import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Servico } from '../../models/servico';
import { ServicoHttpService } from '../../services/servico-http.service';
import { DialogExcluirComponent } from '../dialog-excluir/dialog-excluir.component';

@Component({
  selector: 'app-servico',
  templateUrl: './servico.component.html',
  styleUrls: ['./servico.component.css']
})
export class ServicoComponent implements OnInit {

  servicos: Servico[] = []

  constructor(
    private service: ServicoHttpService,
    private dialog: MatDialog,
    private snack: MatSnackBar
  ) { }

  ngOnInit(): void {

   this.service.getServicos().subscribe(item => this.servicos = item) 

  }

  openDeleteDialog(id: number): void{
    const dialogRef = this.dialog.open(DialogExcluirComponent, {
      data:{
        ...this.servicos[id]
      }
    })

    dialogRef.afterClosed().subscribe(
      result => {
        if(result){
          this.service.deleteServico(result)
          .subscribe(()=> {
            this.snack.open("Serviço deletado!", "OK",  {
              duration: 3000,
              verticalPosition: "top"
            })
            this.service.getServicos().subscribe(item => this.servicos = item)
          }) 
        }
      }
    )
  }

}
