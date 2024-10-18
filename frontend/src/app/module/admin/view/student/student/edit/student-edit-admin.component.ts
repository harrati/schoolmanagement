import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {StudentAdminService} from 'src/app/shared/service/admin/student/StudentAdmin.service';
import {StudentDto} from 'src/app/shared/model/student/Student.model';
import {StudentCriteria} from 'src/app/shared/criteria/student/StudentCriteria.model';


import {PaymentDto} from 'src/app/shared/model/student/Payment.model';
import {PaymentAdminService} from 'src/app/shared/service/admin/student/PaymentAdmin.service';
import {SchoolDto} from 'src/app/shared/model/school/School.model';
import {SchoolAdminService} from 'src/app/shared/service/admin/school/SchoolAdmin.service';

@Component({
  selector: 'app-student-edit-admin',
  templateUrl: './student-edit-admin.component.html'
})
export class StudentEditAdminComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;

    protected paymentsIndex = -1;

    private _paymentsElement = new PaymentDto();





    constructor(private service: StudentAdminService , private paymentService: PaymentAdminService, private schoolService: SchoolAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {

        this.schoolService.findAll().subscribe((data) => this.schools = data);
    }

    public prepareEdit() {
        this.item.registrationDate = this.service.format(this.item.registrationDate);
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new StudentDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validatePayments(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
    }

    public addPayments() {
        if( this.item.payments == null )
            this.item.payments = new Array<PaymentDto>();

       this.validatePayments();

       if (this.errorMessages.length === 0) {
            if (this.paymentsIndex == -1){
                this.item.payments.push({... this.paymentsElement});
            }else {
                this.item.payments[this.paymentsIndex] =this.paymentsElement;
            }
              this.paymentsElement = new PaymentDto();
              this.paymentsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deletePayments(p: PaymentDto, index: number) {
        this.item.payments.splice(index, 1);
    }

    public editPayments(p: PaymentDto, index: number) {
        this.paymentsElement = {... p};
        this.paymentsIndex = index;
        this.activeTab = 0;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
    }





    get school(): SchoolDto {
        return this.schoolService.item;
    }
    set school(value: SchoolDto) {
        this.schoolService.item = value;
    }
    get schools(): Array<SchoolDto> {
        return this.schoolService.items;
    }
    set schools(value: Array<SchoolDto>) {
        this.schoolService.items = value;
    }
    get createSchoolDialog(): boolean {
        return this.schoolService.createDialog;
    }
    set createSchoolDialog(value: boolean) {
        this.schoolService.createDialog= value;
    }

    get paymentsElement(): PaymentDto {
        if( this._paymentsElement == null )
            this._paymentsElement = new PaymentDto();
         return this._paymentsElement;
    }

    set paymentsElement(value: PaymentDto) {
        this._paymentsElement = value;
    }



	get items(): Array<StudentDto> {
        return this.service.items;
    }

    set items(value: Array<StudentDto>) {
        this.service.items = value;
    }

    get item(): StudentDto {
        return this.service.item;
    }

    set item(value: StudentDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): StudentCriteria {
        return this.service.criteria;
    }

    set criteria(value: StudentCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }


}
