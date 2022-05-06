import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Servico } from '../models/servico';

@Injectable({
  providedIn: 'root'
})
export class ServicoHttpService {

  private readonly url = "http://localhost:8080/servicos";

  constructor(
    private http: HttpClient
  ) { }


  getServicos(): Observable<Servico[]> {
    return this.http.get<Servico[]>(`${this.url}/servico`);
  }

  getServicoById(id: number): Observable<Servico>{
    return this.http.get<Servico>(`${this.url}/servico/${id}`);
  }

  postServico(Servico: Servico): Observable<Servico>{
    return this.http.post<Servico>(`${this.url}/servico`, Servico);
  }

  updateServico(Servico: Servico, id: number, idF: number): Observable<Servico>{
    return this.http.put<Servico>(`${this.url}/servico/${id}/${idF}`, Servico);
  }

  deleteServico(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/servico/${id}`);
  }
}
