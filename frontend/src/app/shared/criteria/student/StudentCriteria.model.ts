import {PaymentCriteria} from './PaymentCriteria.model';
import {SchoolCriteria} from '../school/SchoolCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class StudentCriteria extends BaseCriteria {

    public firstName: string;
    public firstNameLike: string;
    public lastName: string;
    public lastNameLike: string;
    public phoneNumber: string;
    public phoneNumberLike: string;
    public registrationDate: Date;
    public registrationDateFrom: Date;
    public registrationDateTo: Date;
    public passwordChanged: null | boolean;
    public accountNonLocked: null | boolean;
    public password: string;
    public passwordLike: string;
    public email: string;
    public emailLike: string;
    public enabled: null | boolean;
    public credentialsNonExpired: null | boolean;
    public accountNonExpired: null | boolean;
    public username: string;
    public usernameLike: string;
      public payments: Array<PaymentCriteria>;

}
