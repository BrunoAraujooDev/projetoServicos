import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/funcionario';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioHttpService {

  private readonly baseURL = "http://localhost:8080/servicos/funcionario";

  constructor(
    private http: HttpClient
  ) { }

  getFuncionarios(): Observable<Funcionario[]>{
    return this.http.get<Funcionario[]>(this.baseURL);
  }

  getFuncionarioById(id: number): Observable<Funcionario>{
    return this.http.get<Funcionario>(`${this.baseURL}/${id}`);
  }

  deleteFuncionario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }

  postFuncionario(funcionario: Funcionario): Observable<Funcionario>{
    return this.http.post<Funcionario>(this.baseURL, funcionario);
  }

  addImage(id: number, data: FormData, fileName: string): Observable<void>{
    return this.http.post<void>(`${this.baseURL}/envioFoto/${id}?nome=${fileName}`, data);
  }

  updateFuncionario( funcionario: Funcionario): Observable<Funcionario>{
    return this.http.put<Funcionario>(`${this.baseURL}/${funcionario.idFuncionario}`, funcionario)
  }

}
