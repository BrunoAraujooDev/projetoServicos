import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IsNumberGuard } from './guards/is-number.guard';
import { AskExitGuard } from './guards/ask-exit.guard';
import { EditarFuncionarioComponent } from './pages/editar-funcionario/editar-funcionario.component';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';
import { ListarFuncionarioComponent } from './pages/listar-funcionario/listar-funcionario.component';
import { NovoFuncionarioComponent } from './pages/novo-funcionario/novo-funcionario.component';
import { CanEnterGuard } from './guards/can-enter.guard';

const routes: Routes = [
  {
    path: 'novo-funcionario',
    component: NovoFuncionarioComponent,
    canDeactivate: [
      AskExitGuard
    ],
    canActivate: [
      CanEnterGuard
    ]
  },
  {
    path: '',
    pathMatch: 'full',
    component: ListarFuncionarioComponent,
    canActivate: [
      CanEnterGuard
    ]
  },
  {
    path: ":idFuncionario", //para declarar que vai receber parâmetro via a rota utiliza os :
    component: FuncionarioComponent,
    canActivate: [
      IsNumberGuard,
      CanEnterGuard
    ]
  },
  {
    path: "edit/:idFuncionario",
    component: EditarFuncionarioComponent,
    canActivate: [
      IsNumberGuard,
      CanEnterGuard
    ],
    canDeactivate: [
      AskExitGuard
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FuncionarioRoutingModule { }
