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
  selector: 'app-school-edit-admin',
  templateUrl: './school-edit-admin.component.html'
})
export class SchoolEditAdminComponent implements OnInit {

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

    protected staffsIndex = -1;
    protected professorsIndex = -1;
    protected studentsIndex = -1;

    private _staffsElement = new StaffDto();
    private _professorsElement = new ProfessorDto();
    private _studentsElement = new StudentDto();





    constructor(private service: SchoolAdminService , private professorService: ProfessorAdminService, private staffService: StaffAdminService, private principalService: PrincipalAdminService, private studentService: StudentAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {



        this.principalService.findAll().subscribe((data) => this.principals = data);
    }

    public prepareEdit() {
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
            this.item = new SchoolDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateStaffs(){
        this.errorMessages = new Array();
    }

    public validateProfessors(){
        this.errorMessages = new Array();
    }

    public validateStudents(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
    }

    public addStaffs() {
        if( this.item.staffs == null )
            this.item.staffs = new Array<StaffDto>();

       this.validateStaffs();

       if (this.errorMessages.length === 0) {
            if (this.staffsIndex == -1){
                this.item.staffs.push({... this.staffsElement});
            }else {
                this.item.staffs[this.staffsIndex] =this.staffsElement;
            }
              this.staffsElement = new StaffDto();
              this.staffsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteStaffs(p: StaffDto, index: number) {
        this.item.staffs.splice(index, 1);
    }

    public editStaffs(p: StaffDto, index: number) {
        this.staffsElement = {... p};
        this.staffsIndex = index;
        this.activeTab = 0;
    }
    public addProfessors() {
        if( this.item.professors == null )
            this.item.professors = new Array<ProfessorDto>();

       this.validateProfessors();

       if (this.errorMessages.length === 0) {
            if (this.professorsIndex == -1){
                this.item.professors.push({... this.professorsElement});
            }else {
                this.item.professors[this.professorsIndex] =this.professorsElement;
            }
              this.professorsElement = new ProfessorDto();
              this.professorsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteProfessors(p: ProfessorDto, index: number) {
        this.item.professors.splice(index, 1);
    }

    public editProfessors(p: ProfessorDto, index: number) {
        this.professorsElement = {... p};
        this.professorsIndex = index;
        this.activeTab = 0;
    }
    public addStudents() {
        if( this.item.students == null )
            this.item.students = new Array<StudentDto>();

       this.validateStudents();

       if (this.errorMessages.length === 0) {
            if (this.studentsIndex == -1){
                this.item.students.push({... this.studentsElement});
            }else {
                this.item.students[this.studentsIndex] =this.studentsElement;
            }
              this.studentsElement = new StudentDto();
              this.studentsIndex = -1;
       }else{
           this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }

    public deleteStudents(p: StudentDto, index: number) {
        this.item.students.splice(index, 1);
    }

    public editStudents(p: StudentDto, index: number) {
        this.studentsElement = {... p};
        this.studentsIndex = index;
        this.activeTab = 0;
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
    }




   public async openCreatePrincipal(principal: string) {
        const isPermistted = await this.roleService.isPermitted('Principal', 'edit');
        if (isPermistted) {
             this.principal = new PrincipalDto();
             this.createPrincipalDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createPrincipalDialog(): boolean {
        return this.principalService.createDialog;
    }
    set createPrincipalDialog(value: boolean) {
        this.principalService.createDialog= value;
    }

    get staffsElement(): StaffDto {
        if( this._staffsElement == null )
            this._staffsElement = new StaffDto();
         return this._staffsElement;
    }

    set staffsElement(value: StaffDto) {
        this._staffsElement = value;
    }
    get professorsElement(): ProfessorDto {
        if( this._professorsElement == null )
            this._professorsElement = new ProfessorDto();
         return this._professorsElement;
    }

    set professorsElement(value: ProfessorDto) {
        this._professorsElement = value;
    }
    get studentsElement(): StudentDto {
        if( this._studentsElement == null )
            this._studentsElement = new StudentDto();
         return this._studentsElement;
    }

    set studentsElement(value: StudentDto) {
        this._studentsElement = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): SchoolCriteria {
        return this.service.criteria;
    }

    set criteria(value: SchoolCriteria) {
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
