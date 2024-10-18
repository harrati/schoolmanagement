import {ProfessorDto} from './Professor.model';
import {StaffDto} from './Staff.model';
import {PrincipalDto} from './Principal.model';
import {StudentDto} from '../student/Student.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class SchoolDto extends BaseDto{

    public name: string;

    public address: string;

    public phoneNumber: string;

    public description: string;

    public principal: PrincipalDto ;
     public staffs: Array<StaffDto>;
     public professors: Array<ProfessorDto>;
     public students: Array<StudentDto>;


    constructor() {
        super();

        this.name = '';
        this.address = '';
        this.phoneNumber = '';
        this.description = '';
        this.staffs = new Array<StaffDto>();
        this.professors = new Array<ProfessorDto>();
        this.students = new Array<StudentDto>();

        }

}
