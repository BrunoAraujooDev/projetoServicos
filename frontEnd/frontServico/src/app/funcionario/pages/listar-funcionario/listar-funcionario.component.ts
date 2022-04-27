import { Component, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';
import { FuncionarioDialogExcluirComponent } from '../funcionario-dialog-excluir/funcionario-dialog-excluir.component';

@Component({
  selector: 'app-listar-funcionario',
  templateUrl: './listar-funcionario.component.html',
  styleUrls: ['./listar-funcionario.component.css']
})
export class ListarFuncionarioComponent implements OnInit {

  funcionarios: Funcionario[] = []

  columns: string[] = ['idFuncionario', 'nome', 'email', 'actions']

  constructor(
    private funHttpService: FuncionarioHttpService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.recoverFuncionarios();
  }

  openDeleteDialog(id: number): void{
    const dialogRef = this.dialog.open(FuncionarioDialogExcluirComponent, {
      data:{
        ...this.funcionarios[id]
      }
    })

    dialogRef.afterClosed().subscribe(
      result => {
        if(result){
          this.funHttpService.deleteFuncionario(result)
          .subscribe(()=> {
            this.snackbar.open("Funcionário deletado!", "OK",  {
              duration: 3000,
              verticalPosition: "top"
            })
            this.recoverFuncionarios
          }) 
        }
      }
    )
  }

  recoverFuncionarios(): void{
    this.funHttpService.getFuncionarios().subscribe(
      (funcionarios) => this.funcionarios = funcionarios
    )
  }
}
