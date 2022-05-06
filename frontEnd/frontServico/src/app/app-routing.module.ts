import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {
        path: 'funcionario',
        loadChildren: () => import('./funcionario/funcionario.module').then((funcionarioModule) => funcionarioModule.FuncionarioModule )
    },
    {
        path: 'auth',
        loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
    },
    {
        path: 'servicos',
        loadChildren: () => import('./servicos/servico.module').then(m => m.ServicoModule)
    }
]

@NgModule({
    declarations: [

    ],
    imports: [
        CommonModule,
        RouterModule.forRoot(routes),
        
    ],
    exports: [
        RouterModule
    ],
    providers: [

    ]
})
export class AppRoutingModule{}