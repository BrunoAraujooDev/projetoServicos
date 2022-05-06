import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CanEnterGuard } from '../funcionario/guards/can-enter.guard';
import { NovoServicoComponent } from './pages/novo-servico/novo-servico.component';
import { ServicoComponent } from './pages/servico/servico.component';

const routes: Routes = [
  {
    path: '',
    component: ServicoComponent,
    canActivate: [
      CanEnterGuard
    ]
  },
  {
    path: 'novo-servico',
    component: NovoServicoComponent,
    canActivate: [
      CanEnterGuard
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServicoRoutingModule { }
