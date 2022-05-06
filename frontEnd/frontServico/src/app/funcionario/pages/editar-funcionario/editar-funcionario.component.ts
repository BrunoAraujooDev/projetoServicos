import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CanDeactivate } from '../../models/canDeactivate';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';
import { DialogVerificationComponent } from '../../components/dialog-verification/dialog-verification.component';

@Component({
  selector: 'app-editar-funcionario',
  templateUrl: './editar-funcionario.component.html',
  styleUrls: ['./editar-funcionario.component.css']
})
export class EditarFuncionarioComponent implements OnInit, CanDeactivate {

  
  funcionario!: Funcionario
  foto!: File

  funcionarioForm: FormGroup = this.fb.group({
    nome: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    foto: [null]
  })



  @ViewChild('fileInput')
  fileInput!: ElementRef

  constructor(
    private route: ActivatedRoute, // permite acessas os parâmetros da rota
    private funHttpService: FuncionarioHttpService,
    private fb: FormBuilder,
    private snack: MatSnackBar,
    private router: Router, //permite fazer roteamento entre as páginas
    private dialog: MatDialog
  ) { }


  canDeactivate(): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    if(this.funcionarioForm.dirty && localStorage.getItem('token') != null){

      const ref = this.dialog.open(DialogVerificationComponent)

      return ref.afterClosed()
    }
    return true;
  }

  ngOnInit(): void {

    const idFuncionario = this.route.snapshot.paramMap.get("idFuncionario") || "";

    this.funHttpService.getFuncionarioById((parseInt(idFuncionario))).subscribe( funcionario =>{
       this.funcionario = funcionario
        this.funcionarioForm.patchValue({
          nome: this.funcionario.nome,
          email: this.funcionario.email,
          foto: this.funcionario.foto
        })
    }
    )
  }

 

  changeImage(event: any): void{
    this.foto = event?.target.files[0]
  }

  submit():void {
    this.funcionario.nome = this.funcionarioForm.value.nome
    this.funcionario.email = this.funcionarioForm.value.email

    this.funHttpService.updateFuncionario(this.funcionario).subscribe(
      () => {
        if(this.foto != undefined){
          const formData = new FormData()

          formData.append("foto", this.foto)

          const fileName = `funcionario-${this.funcionario.idFuncionario}.${this.foto.type.split('/')[1]}`

          this.funHttpService.addImage(this.funcionario.idFuncionario || 0, formData, fileName).subscribe(
            () => {
              this.funcionarioForm.reset()
              this.showMessage()
            }
          ), (e: HttpErrorResponse) => this.snack.open(`Ocorreu um erro ao atualizar o funcionário! Erro: ${e.status}`, "OK", { duration: 2000 })
        } else {
          this.funcionarioForm.reset()
          this.showMessage();
        }
      }, (e: HttpErrorResponse) => this.snack.open(`Ocorreu um erro ao atualizar o funcionário! Erro: ${e.status}`, "OK", { duration: 2000 })
    )
  }

  showMessage(): void{
    this.snack.open("Funcionário atualizado com sucesso!", "OK", { duration: 2000 })
    this.router.navigateByUrl("/funcionario")
  }


}
