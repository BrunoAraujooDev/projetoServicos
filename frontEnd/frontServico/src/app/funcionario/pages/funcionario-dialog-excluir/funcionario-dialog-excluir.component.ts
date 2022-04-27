import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Funcionario } from '../../models/funcionario';

@Component({
  selector: 'app-funcionario-dialog-excluir',
  templateUrl: './funcionario-dialog-excluir.component.html',
  styleUrls: ['./funcionario-dialog-excluir.component.css']
})
export class FuncionarioDialogExcluirComponent {

  constructor(
    public dialogRef: MatDialogRef<FuncionarioDialogExcluirComponent>,
    @Inject(MAT_DIALOG_DATA) public funcionario: Funcionario
  ) { }


}
