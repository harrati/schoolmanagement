import {StudentDto} from './Student.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class PaymentDto extends BaseDto{

    public amount: null | number;

   public paymentDate: Date;

    public student: StudentDto ;


    constructor() {
        super();

        this.amount = null;
        this.paymentDate = null;
        this.student = new StudentDto() ;

        }

}
