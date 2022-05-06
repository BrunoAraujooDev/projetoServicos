import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FuncionarioRoutingModule } from './funcionario-routing.module';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';
import { MaterialModule } from '../material/material.module';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { HttpClientModule } from '@angular/common/http';
import { FuncionarioHttpService } from './services/funcionario-http.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { IsNumberGuard } from './guards/is-number.guard';
import { EditarFuncionarioComponent } from './pages/editar-funcionario/editar-funcionario.component';
import { FuncionarioDialogExcluirComponent } from './pages/funcionario-dialog-excluir/funcionario-dialog-excluir.component';
import { DialogVerificationComponent } from './components/dialog-verification/dialog-verification.component';
import { AskExitGuard } from './guards/ask-exit.guard';
import { CanEnterGuard } from './guards/can-enter.guard';


@NgModule({
  declarations: [
    NovoFuncionarioComponent,
    ListarFuncionarioComponent,
    FuncionarioComponent,
    EditarFuncionarioComponent,
    FuncionarioDialogExcluirComponent,
    DialogVerificationComponent
  ],
  imports: [
    CommonModule,
    FuncionarioRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    IsNumberGuard,
    AskExitGuard,
    CanEnterGuard
  ]
})
export class FuncionarioModule { }
