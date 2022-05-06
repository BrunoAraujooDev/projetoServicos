import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-novo-servico',
  templateUrl: './novo-servico.component.html',
  styleUrls: ['./novo-servico.component.css']
})
export class NovoServicoComponent implements OnInit {

  servicoForm: FormGroup = this.fb.group({
    titulo: ['', [Validators.required]],
    descricao: ['', [Validators.required]],
    status: ['', [Validators.required]],
    dataEntrada: ['', [Validators.required]],
    funcionario: ['']
  })

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
  }

  submit(){

  }

}
