import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Funcionario } from '../../models/funcionario';

@Component({
  selector: 'app-novo-funcionario',
  templateUrl: './novo-funcionario.component.html',
  styleUrls: ['./novo-funcionario.component.css']
})
export class NovoFuncionarioComponent implements OnInit {

  funcionarioData: FormGroup = this.fb.group({
    nome: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    foto: ['', [Validators.required]]
  })

  funcionario: Funcionario[] = []

  @ViewChild('fileInput')
  fileInput!: ElementRef

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
  }

  selectImage(): void{
    this.fileInput.nativeElement.click()
  }

  cadastrarFuncionario(): Funcionario[]{
    
    this.funcionario= [{
      ...this.funcionarioData.value
    }]

    console.log(this.funcionario)

    return this.funcionario
  }
}
