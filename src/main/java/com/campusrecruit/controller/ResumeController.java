package com.campusrecruit.controller;

import com.campusrecruit.pojo.DO.*;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.pojo.VO.ResumeVO;
import com.campusrecruit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private JobIntentionService jobIntentionService;

    @Autowired
    private EducationExperienceService educationExperienceService;

    @Autowired
    private InternshipExperienceService internshipExperienceService;

    @Autowired
    private ProjectExperienceService projectExperienceService;

    @Autowired
    private ProfessionalSkillService professionalSkillService;

    /**
     * 查询在线简历
     *
     * @param userId
     * @return
     */
    @PostMapping
    public ResultMap selectResume(@RequestParam("userId") String userId) {

        ResumeVO resumeVO = getResumeVoByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setData(resumeVO);
        resultMap.setMessage("查询在线简历成功");
        return resultMap;
    }


    /**
     * 插入技能
     * @param userId
     * @param skillName
     * @param description
     * @return
     */
    @PostMapping("/saveProSkill")
    public ResultMap saveProSkill(@RequestParam("userId") String userId,
                                @RequestParam("skillName") String skillName,
                                @RequestParam("description") String description){
        ProfessionalSkill proskill = new ProfessionalSkill();
        proskill.setUserId(Integer.valueOf(userId)).setSkillName(skillName).setDescription(description);
        professionalSkillService.insertOne(proskill);
        ResumeVO resumeVO = getResumeVoByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("保存成功");
        return resultMap;
    }

    /**
     * 更新技能
     * @param id
     * @param skillName
     * @param description
     * @return
     */
    @PostMapping("/updateProSkill")
    public ResultMap updateProSkill(@RequestParam("id") String id,
                                  @RequestParam("skillName") String skillName,
                                  @RequestParam("description") String description) {
        ProfessionalSkill proSkill = professionalSkillService.selectById(Integer.valueOf(id));
        proSkill.setSkillName(skillName).setDescription(description);
        professionalSkillService.updateOne(proSkill);
        ResumeVO resumeVO = getResumeVoByUserId(Integer.valueOf(proSkill.getUserId()));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("更新成功");
        return resultMap;

    }

    /**
     * 删除技能
     * @param id
     * @return
     */
    @PostMapping("/deleteProSkill")
    public ResultMap deleteProSkill(@RequestParam("id") String id){
        ProfessionalSkill professionalSkill = professionalSkillService.selectById(Integer.valueOf(id));
        ResumeVO resumeVO = getResumeVoByUserId(professionalSkill.getUserId());
        int res = professionalSkillService.deleteOne(Integer.valueOf(id));
        ResultMap resultMap = new ResultMap();

        if (res > 0) {

            resultMap.setCode(200);
            resultMap.setData(resumeVO);
            resultMap.setResult("success");
            resultMap.setMessage("删除成功");
        } else {
            resultMap.setCode(201);
            resultMap.setData(resumeVO);
            resultMap.setResult("error");
            resultMap.setMessage("删除失败");
        }
        return resultMap;
    }

    /**
     * 保存项目经历
     * @param userId
     * @param projectName
     * @param postion
     * @param startDate
     * @param endDate
     * @param description
     * @return
     */
    @PostMapping("/saveProExp")
    public ResultMap saveProExp(@RequestParam("userId") String userId,
                                @RequestParam("projectName") String projectName,
                                @RequestParam("postion") String postion,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                @RequestParam("description") String description) {
        ProjectExperience proExp = new ProjectExperience();
        proExp.setUserId(Integer.valueOf(userId));
        proExp.setProjectName(projectName);
        proExp.setPostion(postion);
        proExp.setStartDate(startDate);
        proExp.setEndDate(endDate);
        proExp.setDescription(description);
        projectExperienceService.insertOne(proExp);
        ResumeVO resumeVO = getResumeVoByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("保存成功");
        return resultMap;
    }


    /**
     * 更新项目经历
     * @param id
     * @param projectName
     * @param postion
     * @param startDate
     * @param endDate
     * @param description
     * @return
     */
    @PostMapping("/updateProExp")
    public ResultMap updateProExp(@RequestParam("id") String id,
                                  @RequestParam("projectName") String projectName,
                                  @RequestParam("postion") String postion,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("description") String description) {
        ProjectExperience proExp = projectExperienceService.selectById(Integer.valueOf(id));
        proExp.setProjectName(projectName);
        proExp.setPostion(postion);
        proExp.setStartDate(startDate);
        proExp.setEndDate(endDate);
        proExp.setDescription(description);
        projectExperienceService.updateOne(proExp);
        ResumeVO resumeVO = getResumeVoByUserId(proExp.getUserId());
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("更新成功");
        return resultMap;

    }
    /**
     * 删除项目经历
     * @param id
     * @return
     */
    @PostMapping("/deleteProExp")
    public ResultMap deleteProExp(@RequestParam("id") String id){
        ResumeVO resumeVO = getResumeVoByUserId(projectExperienceService.selectById(Integer.valueOf(id)).getUserId());
        int res = projectExperienceService.deleteOne(Integer.valueOf(id));
        ResultMap resultMap = new ResultMap();

        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setData(resumeVO);
            resultMap.setResult("success");
            resultMap.setMessage("删除成功");
        } else {
            resultMap.setCode(201);
            resultMap.setData(resumeVO);
            resultMap.setResult("error");
            resultMap.setMessage("删除失败");
        }
        return resultMap;
    }

    /**
     * 删除实习经历
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteIntExp")
    public ResultMap deleteIntExp(@RequestParam("id") String id) {
        ResumeVO resumeVO = getResumeVoByUserId(internshipExperienceService.selectById(Integer.valueOf(id)).getUserId());
        int res = internshipExperienceService.deleteOne(Integer.valueOf(id));

        ResultMap resultMap = new ResultMap();
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setData(resumeVO);
            resultMap.setResult("success");
            resultMap.setMessage("删除成功");
        } else {
            resultMap.setCode(201);
            resultMap.setData(resumeVO);
            resultMap.setResult("error");
            resultMap.setMessage("删除失败");
        }
        return resultMap;
    }

    /**
     * 更新实习经历
     *
     * @param id
     * @param companyName
     * @param postion
     * @param startDate
     * @param endDate
     * @param description
     * @return
     */
    @PostMapping("/updateIntExp")
    public ResultMap updateIntExp(@RequestParam("id") String id,
                                  @RequestParam("companyName") String companyName,
                                  @RequestParam("postion") String postion,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("description") String description) {
        InternshipExperience intExp = internshipExperienceService.selectById(Integer.valueOf(id));
        intExp.setCompanyName(companyName).setPostion(postion).setStartDate(startDate).setEndDate(endDate).setDescription(description);
        internshipExperienceService.updateOne(intExp);
        ResumeVO resumeVO = getResumeVoByUserId(intExp.getUserId());
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("更新成功");
        return resultMap;

    }

    /**
     * 保存实习经历
     *
     * @param userId
     * @param companyName
     * @param postion
     * @param startDate
     * @param endDate
     * @param description
     * @return
     */
    @PostMapping("/saveIntExp")
    public ResultMap saveIntExp(@RequestParam("userId") String userId,
                                @RequestParam("companyName") String companyName,
                                @RequestParam("postion") String postion,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                @RequestParam("description") String description) {
        InternshipExperience intExp = new InternshipExperience();
        intExp.setUserId(Integer.valueOf(userId)).setCompanyName(companyName).setPostion(postion).setStartDate(startDate).setEndDate(endDate).setDescription(description);
        internshipExperienceService.insertOne(intExp);
        ResumeVO resumeVO = getResumeVoByUserId(intExp.getUserId());
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("保存成功");
        return resultMap;

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteEduExp")
    public ResultMap deleteEduExp(@RequestParam("id") String id) {
        ResumeVO resumeVO = getResumeVoByUserId(educationExperienceService.selectById(Integer.valueOf(id)).getUserId());
        int res = educationExperienceService.deleteOne(Integer.valueOf(id));
        ResultMap resultMap = new ResultMap();
        resultMap.setData(resumeVO);
        if (res > 0) {

            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("删除教育经历成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("删除教育经历失败");
        }
        return resultMap;
    }

    /**
     * 插入教育经历
     *
     * @param userId
     * @param schoolName
     * @param major
     * @param degree
     * @param startDate
     * @param endDate
     * @param description
     * @return
     */
    @PostMapping("/saveEduExp")
    public ResultMap saveEduExp(@RequestParam("userId") String userId,
                                @RequestParam("schoolName") String schoolName,
                                @RequestParam("major") String major,
                                @RequestParam("degree") String degree,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                @RequestParam("description") String description) {
        EducationExperience eduExp = new EducationExperience();
        eduExp.setUserId(Integer.valueOf(userId));
        eduExp.setSchoolName(schoolName);
        eduExp.setMajor(major);
        eduExp.setDegree(degree);
        eduExp.setStartDate(startDate);
        eduExp.setEndDate(endDate);
        eduExp.setDescription(description);
        educationExperienceService.insertOne(eduExp);
        ResumeVO resumeVO = getResumeVoByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(resumeVO);
        resultMap.setResult("success");
        resultMap.setMessage("保存成功");
        return resultMap;

    }

    @PostMapping("/updateEduExp")
    public ResultMap updateEduExp(@RequestParam("id") String id,
                                  @RequestParam("schoolName") String schoolName,
                                  @RequestParam("major") String major,
                                  @RequestParam("degree") String degree,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("description") String description) {
        EducationExperience eduExp = educationExperienceService.selectById(Integer.valueOf(id));
        eduExp.setSchoolName(schoolName);
        eduExp.setMajor(major);
        eduExp.setDegree(degree);
        eduExp.setStartDate(startDate);
        eduExp.setEndDate(endDate);
        eduExp.setDescription(description);
        int res = educationExperienceService.updateOne(eduExp);
        ResumeVO resumeVO = getResumeVoByUserId(eduExp.getUserId());
        ResultMap resultMap = new ResultMap();
        resultMap.setData(resumeVO);
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("保存教育经历成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("保存教育经历失败");
        }

        return resultMap;

    }

    /**
     * 保存意向
     *
     * @param userId
     * @param trade
     * @param postion
     * @param salary
     * @param location
     * @return
     */
    @PostMapping("/saveJobIntention")
    public ResultMap saveJobIntention(@RequestParam("userId") String userId,
                                      @RequestParam("trade") String trade,
                                      @RequestParam("postion") String postion,
                                      @RequestParam("salary") String salary,
                                      @RequestParam("location") String location) {
        JobIntention jobIntention = jobIntentionService.selectByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        ResumeVO resumeVO = getResumeVoByUserId(jobIntention.getUserId());
        resultMap.setData(resumeVO);
        if (jobIntention == null) {
            jobIntention = new JobIntention();
            jobIntention.setUserId(Integer.valueOf(userId));
            jobIntention.setTrade(trade);
            jobIntention.setPostion(postion);
            jobIntention.setSalary(salary);
            jobIntention.setLocation(location);
            jobIntentionService.insertOne(jobIntention);
        } else {
            jobIntention.setTrade(trade);
            jobIntention.setPostion(postion);
            jobIntention.setSalary(salary);
            jobIntention.setLocation(location);
            jobIntentionService.updateOne(jobIntention);
        }

        resultMap.setCode(200);
        resultMap.setMessage("保存求职意向成功");
        resultMap.setResult("success");
        return resultMap;
    }
    public ResumeVO getResumeVoByUserId(Integer uid){
        JobIntention jobInt = jobIntentionService.selectByUserId(uid);
        List<EducationExperience> eduExps = educationExperienceService.selectByUserId(uid);
        List<InternshipExperience> intExps = internshipExperienceService.selectByUserId(uid);
        List<ProjectExperience> proExps = projectExperienceService.selectByUserId(uid);
        List<ProfessionalSkill> proSkills = professionalSkillService.selectByUserId(uid);
        ResumeVO resumeVO = new ResumeVO();
        resumeVO.setEduExps(eduExps).setJobIntention(jobInt).setIntExps(intExps).setProExps(proExps).setProSkills(proSkills);
        return resumeVO;
    }
}
