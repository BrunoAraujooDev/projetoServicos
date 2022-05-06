import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Servico } from '../../models/servico';

@Component({
  selector: 'app-dialog-excluir',
  templateUrl: './dialog-excluir.component.html',
  styleUrls: ['./dialog-excluir.component.css']
})
export class DialogExcluirComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DialogExcluirComponent>,
    @Inject(MAT_DIALOG_DATA) public servico: Servico
  ) { }

  ngOnInit(): void {
  }

}
