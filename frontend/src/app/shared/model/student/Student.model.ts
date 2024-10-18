import {PaymentDto} from './Payment.model';
import {SchoolDto} from '../school/School.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class StudentDto extends BaseDto{

    public firstName: string;

    public lastName: string;

    public phoneNumber: string;

   public registrationDate: Date;

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;

    public email: string;

   public enabled: null | boolean;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;

    public school: SchoolDto ;
     public payments: Array<PaymentDto>;


    constructor() {
        super();

        this.firstName = '';
        this.lastName = '';
        this.phoneNumber = '';
        this.registrationDate = null;
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.payments = new Array<PaymentDto>();

        }

}
