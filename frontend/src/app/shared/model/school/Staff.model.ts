
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class StaffDto extends BaseDto{

    public firstName: string;

    public lastName: string;

    public phoneNumber: string;

    public role: string;

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;

    public email: string;

   public enabled: null | boolean;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;



    constructor() {
        super();

        this.firstName = '';
        this.lastName = '';
        this.phoneNumber = '';
        this.role = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';

        }

}
