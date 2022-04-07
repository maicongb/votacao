import { Enquete } from './enquete';

export interface Candidato {
  id: number;
  nome: string;
  apelido: string;
  partido: string;
  numero: number;
  telefone: string;
  ativo: number;
  localidade: string;
  descricao: string;
  email: string;
  enquetes: Enquete[];
}
