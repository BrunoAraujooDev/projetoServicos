import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-novo-funcionario',
  templateUrl: './novo-funcionario.component.html',
  styleUrls: ['./novo-funcionario.component.css']
})
export class NovoFuncionarioComponent implements OnInit {

  funcionarioData: FormGroup = this.fb.group({
    nome: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    foto: ['']
  })

  @ViewChild('fileInput')
  fileInput!: ElementRef

  foto!: File

  constructor(
    private fb: FormBuilder,
    private http: FuncionarioHttpService,
    private snackbar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  selectImage(): void {
    this.fileInput.nativeElement.click()
  }

  submitFuncionario(): void {


    const funcionario = this.funcionarioData.value
    funcionario.foto = null

    this.http.postFuncionario(funcionario).subscribe((funcionario) => {

      if (this.foto) {
        const formData: FormData = new FormData()
        formData.append("foto", new Blob([this.foto], { type: this.foto.type }))

        const fileName = `funcionario-${funcionario.idFuncionario}.${this.foto.type.split("/")[1]}`

        this.http.addImage(funcionario.idFuncionario || 0, formData, fileName).subscribe(() => {
          this.showMessage()
        },
          (e: HttpErrorResponse) => this.snackbar.open(`Ocorreu um erro ao salvar! Erro ${e.status}`, "OK", { duration: 2000 })
        )
      } else {
        this.showMessage()
      }

    },
      (e: HttpErrorResponse) => this.snackbar.open(`Ocorreu um erro ao salvar! Erro ${e.status}`, "OK", { duration: 2000 })
    )
  }

  fileChange(event: any) {
    this.foto = event.target.files[0]
  }

  showMessage(): void{
    this.snackbar.open("Funcionário salvo com sucesso!", "OK", { duration: 2000 })
    this.router.navigateByUrl("/funcionario")
  }

}
