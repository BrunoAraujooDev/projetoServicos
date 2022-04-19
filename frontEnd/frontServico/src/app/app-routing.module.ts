import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {
        path: 'funcionario',
        loadChildren: () => import('./funcionario/funcionario.module').then((funcionarioModule) => funcionarioModule.FuncionarioModule )
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