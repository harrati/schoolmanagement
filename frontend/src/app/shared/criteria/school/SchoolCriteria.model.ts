import {ProfessorCriteria} from './ProfessorCriteria.model';
import {StaffCriteria} from './StaffCriteria.model';
import {PrincipalCriteria} from './PrincipalCriteria.model';
import {StudentCriteria} from '../student/StudentCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class SchoolCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public address: string;
    public addressLike: string;
    public phoneNumber: string;
    public phoneNumberLike: string;
    public description: string;
    public descriptionLike: string;
      public staffs: Array<StaffCriteria>;
      public professors: Array<ProfessorCriteria>;
      public students: Array<StudentCriteria>;

}
