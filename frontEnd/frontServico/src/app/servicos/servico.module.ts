import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicoRoutingModule } from './servico-routing.module';
import { ServicoComponent } from './pages/servico/servico.component';
import { MaterialModule } from '../material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CanEnterGuard } from '../funcionario/guards/can-enter.guard';
import { DialogExcluirComponent } from './pages/dialog-excluir/dialog-excluir.component';
import { NovoServicoComponent } from './pages/novo-servico/novo-servico.component';



@NgModule({
  declarations: [
    ServicoComponent,
    DialogExcluirComponent,
    NovoServicoComponent
  ],
  imports: [
    CommonModule,
    ServicoRoutingModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    CanEnterGuard
  ]
})
export class ServicoModule { }
