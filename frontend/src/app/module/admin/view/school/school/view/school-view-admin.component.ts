import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {SchoolAdminService} from 'src/app/shared/service/admin/school/SchoolAdmin.service';
import {SchoolDto} from 'src/app/shared/model/school/School.model';
import {SchoolCriteria} from 'src/app/shared/criteria/school/SchoolCriteria.model';

import {ProfessorDto} from 'src/app/shared/model/school/Professor.model';
import {ProfessorAdminService} from 'src/app/shared/service/admin/school/ProfessorAdmin.service';
import {PaymentDto} from 'src/app/shared/model/student/Payment.model';
import {PaymentAdminService} from 'src/app/shared/service/admin/student/PaymentAdmin.service';
import {StaffDto} from 'src/app/shared/model/school/Staff.model';
import {StaffAdminService} from 'src/app/shared/service/admin/school/StaffAdmin.service';
import {PrincipalDto} from 'src/app/shared/model/school/Principal.model';
import {PrincipalAdminService} from 'src/app/shared/service/admin/school/PrincipalAdmin.service';
import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentAdminService} from 'src/app/shared/service/admin/student/StudentAdmin.service';
@Component({
  selector: 'app-school-view-admin',
  templateUrl: './school-view-admin.component.html'
})
export class SchoolViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    staffs = new StaffDto();
    staffss: Array<StaffDto> = [];
    professors = new ProfessorDto();
    professorss: Array<ProfessorDto> = [];
    students = new StudentDto();
    studentss: Array<StudentDto> = [];

    constructor(private service: SchoolAdminService, private professorService: ProfessorAdminService, private staffService: StaffAdminService, private principalService: PrincipalAdminService, private studentService: StudentAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get principal(): PrincipalDto {
        return this.principalService.item;
    }
    set principal(value: PrincipalDto) {
        this.principalService.item = value;
    }
    get principals(): Array<PrincipalDto> {
        return this.principalService.items;
    }
    set principals(value: Array<PrincipalDto>) {
        this.principalService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<SchoolDto> {
        return this.service.items;
    }

    set items(value: Array<SchoolDto>) {
        this.service.items = value;
    }

    get item(): SchoolDto {
        return this.service.item;
    }

    set item(value: SchoolDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): SchoolCriteria {
        return this.service.criteria;
    }

    set criteria(value: SchoolCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
