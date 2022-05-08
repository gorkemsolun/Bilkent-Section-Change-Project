package tr.edu.bilkent.cs.cs102.registerplusplus.server.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.cs.cs102.registerplusplus.server.entity.*;
import tr.edu.bilkent.cs.cs102.registerplusplus.server.repo.ForumRequestRepository;
import tr.edu.bilkent.cs.cs102.registerplusplus.server.repo.MultipleRequestRepository;
import tr.edu.bilkent.cs.cs102.registerplusplus.server.repo.SingleRequestRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RequestProcessorService {

    private final CourseService courseService;

    private final SingleRequestRepository singleRequestRepository;

    private final MultipleRequestRepository multipleRequestRepository;

    private final ForumRequestRepository forumRequestRepository;


    public RequestProcessorService(CourseService courseService, SingleRequestRepository singleRequestRepository, MultipleRequestRepository multipleRequestRepository, ForumRequestRepository forumRequestRepository) {
        this.courseService = courseService;
        this.singleRequestRepository = singleRequestRepository;
        this.multipleRequestRepository = multipleRequestRepository;
        this.forumRequestRepository = forumRequestRepository;
    }

    public void processNonForumRequests() {
        List<SingleRequest> singleRequests = singleRequestRepository.findAll();
        List<MultipleRequest> multipleRequests = multipleRequestRepository.findAll();
        Collections.sort(singleRequests);
        Collections.sort(multipleRequests);
        int i1 = 0, i2 = 0;
        while (i1 < singleRequests.size() && i2 < multipleRequests.size()) {
            SingleRequest singleRequest = singleRequests.get(i1);
            MultipleRequest multipleRequest = multipleRequests.get(i2);
            if (singleRequest.compareTo(multipleRequest) <= 0) {
                if (!isStillValid(singleRequest.getWantedCourse(), singleRequest.getRequestOwner(),courseService.getCourseByStudentId(singleRequest.getRequestOwner()))) {
                    singleRequestRepository.delete(singleRequest);
                    continue;
                }
                boolean b = processSingleRequest(singleRequest);
                if (b) {
                    i1 = 0;
                    i2 = 0;
                    singleRequests = singleRequestRepository.findAll();
                    continue;
                }
                i1++;
            } 
            else {
                if (isStillValid(multipleRequest.getWantedCourses(), multipleRequest.getRequestOwner(), courseService.getCourseByStudentId(multipleRequest.getRequestOwner()))) {
                    multipleRequestRepository.delete(multipleRequest);
                    continue;
                }
                boolean b = processMultipleRequest(multipleRequest);
                if (b) {
                    i1 = 0;
                    i2 = 0;
                    multipleRequests = multipleRequestRepository.findAll();
                    continue;
                }
                i2++;
            }
        }
        while (i1 < singleRequests.size()) {
            SingleRequest singleRequest = singleRequests.get(i1);
            if (!isStillValid(singleRequest.getWantedCourse(), singleRequest.getRequestOwner(),courseService.getCourseByStudentId(singleRequest.getRequestOwner()))) {
                singleRequestRepository.delete(singleRequest);
                continue;
            }
            boolean b = processSingleRequest(singleRequest);
            if (b) {
                i1 = 0;
                singleRequests = singleRequestRepository.findAll();
                continue;
            }
            i1++;
        }
        while (i2 < multipleRequests.size()) {
            MultipleRequest multipleRequest = multipleRequests.get(i2);
            if (isStillValid(multipleRequest.getWantedCourses(), multipleRequest.getRequestOwner(), courseService.getCourseByStudentId(multipleRequest.getRequestOwner()))) {
                multipleRequestRepository.delete(multipleRequest);
                continue;
            }
            boolean b = processMultipleRequest(multipleRequest);
            if (b) {
                i2 = 0;
                multipleRequests = multipleRequestRepository.findAll();
                continue;
            }
            i2++;
        }
    }

    private boolean processSingleRequest(SingleRequest req) {
        Student owner = req.getRequestOwner();
        List<Course> courseByStudentId = courseService.getCourseByStudentId(owner);
        Course wanted = req.getWantedCourse();
        /*if (courseByStudentId.contains(wanted)) {
            singleRequestRepository.delete(req);
            return true;
        }*/
        if (isFull(wanted)) return false;

        if (doesStudentTakeCourse(wanted, courseByStudentId)) {
            return processSectionChangeRequest(req, courseByStudentId);
        }
        return processNewCourseRequest(req, courseByStudentId);
    }

    private boolean processNewCourseRequest(SingleRequest req, List<Course> courseByStudentId) {
        //TODO if owner loses one of the offering courses in the forum 
        //or one of the new courses start to overlap with wanted course in the forum
        //this forum request should be deleted immediately

        Student owner = req.getRequestOwner();
        Course wanted = req.getWantedCourse();
        if (doesOverlap(wanted, owner, courseByStudentId)) {
            singleRequestRepository.delete(req);
            return true;
        }
        courseService.addStudentToCourse(owner, wanted);
        singleRequestRepository.delete(req);
        return true;
    }

    private boolean processSectionChangeRequest(SingleRequest req, List<Course> courseByStudentId) {
        //TODO if owner loses one of the offering courses in the forum 
        //or one of the new courses start to overlap with wanted course in the forum
        //this forum request should be deleted immediately
        Student owner = req.getRequestOwner();
        Course wanted = req.getWantedCourse();
        Course course = courseByStudentId.stream().filter(c -> c.getName().equals(wanted.getName())).findAny().get(); //todo isPresent check?
        courseService.removeStudentFromCourse(owner, course);
        courseService.addStudentToCourse(owner, wanted);
        singleRequestRepository.delete(req);
        return true;
    }

    /*private boolean processMultipleRequest(MultipleRequest req) {
        //TODO if owner loses one of the offering courses in the forum 
        //or one of the new courses start to overlap with wanted course in the forum
        //this forum request should be deleted immediately

        //TODO remove the controls
        List<Course> wantedCourses = req.getWantedCourses();
        Student owner = req.getRequestOwner();
        List<Course> courseByStudentId = courseService.getCourseByStudentId(owner);
        List<Course> newCourses = new ArrayList<>();
        List<Course> alreadyTaking = new ArrayList<>();
        for (Course c : wantedCourses) {
            if (isFull(c)) {
                return false;
            }
            if (courseByStudentId.contains(c)) {
                alreadyTaking.add(c);
            } else {
                newCourses.add(c);
            }
        }
        boolean[][] tempProgram = cloneProgramOfStudent(owner);
        for (Course c : alreadyTaking) {
            Course course = courseByStudentId.stream().filter(co -> co.getName().equals(c.getName())).findAny().get();
            removeCourseFromTempProgram(course, tempProgram);
        }
        for (Course c : wantedCourses) {
            boolean[][] wantedProgram = c.getProgram();
            for (int i = 0; i < tempProgram.length; i++) {
                for (int j = 0; j < tempProgram[i].length; j++) {
                    if (wantedProgram[i][j] && tempProgram[i][j]) {
                        return false;
                    }
                    if (!tempProgram[i][j] && wantedProgram[i][j]) {
                        tempProgram[i][j] = true;
                    }
                }
            }
        }
        for (Course c : newCourses) {
            courseService.addStudentToCourse(owner, c);
        }
        for (Course c : alreadyTaking) {
            Course changingCourse = courseByStudentId.stream().filter(co -> co.getName().equals(c.getName())).findAny().get();
            courseService.removeStudentFromCourse(owner, changingCourse);
            courseService.addStudentToCourse(owner, c);
        }
        multipleRequestRepository.delete(req);
        return true;
    }*/

    private boolean processMultipleRequest (MultipleRequest req) {
        List<Course> wantedCourses = req.getWantedCourses();
        Student owner = req.getRequestOwner();
        List<Course> courseByStudentId = courseService.getCourseByStudentId(owner);
        for (int p = 0; p < wantedCourses.size(); p++) {
            if (isFull(wantedCourses.get(p))) {
                return false;
            }
        }
        for (int p = 0; p < wantedCourses.size(); p++) {
            Course wanted = wantedCourses.get(p);
            Course removeThat = null;
            for (int j = 0; j < courseByStudentId.size(); j++) {
                if (courseByStudentId.get(j).getName().equals(wanted.getName())) {
                    removeThat = courseByStudentId.get(j);
                }
            }
            if (doesStudentTakeCourse(removeThat, courseByStudentId)) {
                courseService.removeStudentFromCourse(owner, removeThat);
            }
            courseService.addStudentToCourse(owner, wanted);
        }
        multipleRequestRepository.delete(req);
        return true;
    }

    public boolean processForumRequest(ForumRequest req, Student acceptor) {
        if (!isForumRequestPossible(req, acceptor)) {
            return false;
        }
        Student owner = req.getRequestOwner();
        //Student acceptor = req.getAcceptor();
        Course ownerCourse = req.getCurrentCourse();
        Course wantedCourse = req.getWantedCourse();
        courseService.removeStudentFromCourse(owner, ownerCourse);
        courseService.removeStudentFromCourse(acceptor, wantedCourse);
        courseService.addStudentToCourse(owner, wantedCourse);
        courseService.addStudentToCourse(acceptor, ownerCourse);
        forumRequestRepository.delete(req);
        processNonForumRequests();
        return true;
    }

    private boolean isForumRequestPossible(ForumRequest req, Student acceptor) {
        boolean[][] acceptorProgram = cloneProgramOfStudent(acceptor);
        boolean[][] currentCourseProgram = req.getCurrentCourse().getProgram();
        removeCourseFromTempProgram(req.getWantedCourse(), acceptorProgram);
        for (int i = 0; i < currentCourseProgram.length; i++) {
            for (int j = 0; j < currentCourseProgram[i].length; j++) {
                if ((acceptorProgram[i][j] && currentCourseProgram[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean[][] cloneProgramOfStudent(Student student) {
        boolean[][] originalProgram = student.getProgram();
        boolean[][] tempProgram = new boolean[originalProgram.length][];
        for (int i = 0; i < originalProgram.length; i++) {
            tempProgram[i] = originalProgram[i].clone();
        }
        return tempProgram;
    }

    private void removeCourseFromTempProgram(Course course, boolean[][] tempProgram) {
        boolean[][] courseProgram = course.getProgram();
        for (int i = 0; i < courseProgram.length; i++) {
            for (int j = 0; j < courseProgram[i].length; j++) {
                if (courseProgram[i][j]) {
                    tempProgram[i][j] = false;
                }
            }
        }
    }

    private boolean doesStudentTakeCourse(Course wanted, List<Course> coursesByStudentId) {
        return coursesByStudentId.stream().anyMatch(c -> c.getName().equals(wanted.getName()));
    }

    public boolean isFull(Course c) {
        return c.getStudents().size() >= Course.QUOTA;
    }

    public boolean doesOverlap(Course course, Student student, List<Course> coursesOfStudent) {
        if (coursesOfStudent.contains(course)) return true;
        boolean[][] studentProgram = student.getProgram();
        boolean[][] courseProgram = course.getProgram();
        for (int i = 1; i < studentProgram.length; i++) {
            for (int j = 1; j < studentProgram[i].length; j++) {
                if (studentProgram[i][j] && courseProgram[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStillValid(Course course, Student student, List<Course> coursesOfStudent) {
        if (coursesOfStudent.contains(course)) return true;
        boolean[][] studentProgram = student.getProgram();
        boolean[][] courseProgram = course.getProgram();
        Course[][] studentSchedule = student.getSchedule();
        for (int i = 1; i < studentProgram.length; i++) {
            for (int j = 1; j < studentProgram[i].length; j++) {
                if (studentSchedule[i][j].getName().equals(course.getName())) {
                    continue;
                }
                if (studentProgram[i][j] && courseProgram[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStillValid(List<Course> wantedCourses, Student student, List<Course> coursesOfStudent) {
        List<Course> noIncluded = new ArrayList<>();

        //finding the courses which is not in the requesting courses and adding them to noIncluded 
        for (int i = 0; i < coursesOfStudent.size(); i++) {
            boolean ctr = true;
            for (int j = 0; j < wantedCourses.size(); j++) {
                if (wantedCourses.get(j).getName().equals(coursesOfStudent.get(i).getName())) {
                    ctr = false;
                }
            }
            if (ctr) {
                noIncluded.add(coursesOfStudent.get(i));
            }
        }

        //checking the compatibility of requesting courses and no included courses
        for (int i = 0; i < wantedCourses.size(); i++) {
            for (int j = 0; j < noIncluded.size(); j++) {
                if (wantedCourses.get(i).doesOverlap(noIncluded.get(j))){
                    return true;
                }
            } 
        }

        //checking the compatibility of requesting courses with each other
        for (int i = 0; i < wantedCourses.size(); i++) {
            for (int j = i+1; j < wantedCourses.size(); j++) {
                if(wantedCourses.get(i).doesOverlap(wantedCourses.get(j))){
                    return true;
                }
            } 
        }

        return false;
    }
}
