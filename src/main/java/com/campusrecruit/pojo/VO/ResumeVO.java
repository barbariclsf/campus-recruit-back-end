package com.campusrecruit.pojo.VO;

import com.campusrecruit.pojo.DO.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResumeVO {
    private JobIntention jobIntention;
    private List<EducationExperience> eduExps;
    private List<InternshipExperience> intExps;
    private List<ProjectExperience> proExps;
    private List<ProfessionalSkill> proSkills;

}
