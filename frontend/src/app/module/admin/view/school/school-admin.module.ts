import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { SchoolCreateAdminComponent } from './school/create/school-create-admin.component';
import { SchoolEditAdminComponent } from './school/edit/school-edit-admin.component';
import { SchoolViewAdminComponent } from './school/view/school-view-admin.component';
import { SchoolListAdminComponent } from './school/list/school-list-admin.component';
import { StaffCreateAdminComponent } from './staff/create/staff-create-admin.component';
import { StaffEditAdminComponent } from './staff/edit/staff-edit-admin.component';
import { StaffViewAdminComponent } from './staff/view/staff-view-admin.component';
import { StaffListAdminComponent } from './staff/list/staff-list-admin.component';
import { ProfessorCreateAdminComponent } from './professor/create/professor-create-admin.component';
import { ProfessorEditAdminComponent } from './professor/edit/professor-edit-admin.component';
import { ProfessorViewAdminComponent } from './professor/view/professor-view-admin.component';
import { ProfessorListAdminComponent } from './professor/list/professor-list-admin.component';
import { PrincipalCreateAdminComponent } from './principal/create/principal-create-admin.component';
import { PrincipalEditAdminComponent } from './principal/edit/principal-edit-admin.component';
import { PrincipalViewAdminComponent } from './principal/view/principal-view-admin.component';
import { PrincipalListAdminComponent } from './principal/list/principal-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    SchoolCreateAdminComponent,
    SchoolListAdminComponent,
    SchoolViewAdminComponent,
    SchoolEditAdminComponent,
    StaffCreateAdminComponent,
    StaffListAdminComponent,
    StaffViewAdminComponent,
    StaffEditAdminComponent,
    ProfessorCreateAdminComponent,
    ProfessorListAdminComponent,
    ProfessorViewAdminComponent,
    ProfessorEditAdminComponent,
    PrincipalCreateAdminComponent,
    PrincipalListAdminComponent,
    PrincipalViewAdminComponent,
    PrincipalEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,


  ],
  exports: [
  SchoolCreateAdminComponent,
  SchoolListAdminComponent,
  SchoolViewAdminComponent,
  SchoolEditAdminComponent,
  StaffCreateAdminComponent,
  StaffListAdminComponent,
  StaffViewAdminComponent,
  StaffEditAdminComponent,
  ProfessorCreateAdminComponent,
  ProfessorListAdminComponent,
  ProfessorViewAdminComponent,
  ProfessorEditAdminComponent,
  PrincipalCreateAdminComponent,
  PrincipalListAdminComponent,
  PrincipalViewAdminComponent,
  PrincipalEditAdminComponent,
  ],
})
export class SchoolAdminModule { }
