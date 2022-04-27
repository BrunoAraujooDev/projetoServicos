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

  constructor(
    private fb: FormBuilder,
    private http: FuncionarioHttpService,
    private snackbar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  selectImage(): void{
    this.fileInput.nativeElement.click()
  }

  cadastrarFuncionario(): void{


    const funcionario: Funcionario = this.funcionarioData.value
    console.log('funcionario', funcionario)
    
    this.http.postFuncionario(funcionario).subscribe(()=> {
      this.snackbar.open("Funcionário salvo com sucesso!", "OK", {duration: 2000})
      this.router.navigateByUrl("/funcionario")
    }, 
     (e: HttpErrorResponse) => console.log(e)
    )
  }
}
