import psycopg2
from faker import Faker
import random

# Configuração da conexão com o PostgreSQL
conn = psycopg2.connect(
    host="localhost",
    database="residenciasSMS",
    user="postgres",
    password="1234"
)

# Criar um cursor para executar comandos SQL
cur = conn.cursor()

# Instanciar a biblioteca Faker
fake = Faker('pt_BR')  # Gera dados fictícios em português


# Função para inserir dados fictícios na tabela residentes
def gerar_residentes(n):
    for _ in range(n):
        nome = fake.name()
        cpf = fake.cpf()
        rg = fake.random_number(digits=10)  # Gera um número com até 15 dígitos
        crm = fake.random_number(digits=5)  # Gera um número com até 15 dígitos
        email = fake.email()
        telefone = fake.phone_number()

        # Truncar valores para garantir que não excedam o comprimento máximo
        cpf = cpf[:14]
        rg = str(rg)[:11]
        crm = str(crm)[:5]
        telefone = telefone[:15]

        cur.execute("""
            INSERT INTO public.residentes (nome_residente, cpf_residente, rg_residente, crm_residente, email_residente, 
            telefone_residente)
            VALUES (%s, %s, %s, %s, %s, %s)
        """, (nome, cpf, rg, crm, email, telefone))

    print(f"{n} residentes inseridos.")


# Função para inserir dados fictícios na tabela residencias
def gerar_residencias(n):
    categorias = ['Ano Adicional', 'Acesso Direto']
    for _ in range(n):
        nome = fake.company() + " Residência"
        apelido = fake.catch_phrase()
        categoria = random.choice(categorias)

        cur.execute("""
            INSERT INTO public.residencias (nome_residencia, apelido_residencia, categoria_residencia)
            VALUES (%s, %s, %s)
        """, (nome, apelido, categoria))

    print(f"{n} residências inseridas.")


# Função para inserir dados fictícios na tabela matriculas

def gerar_matriculas(n):
    for _ in range(n):
        id_residente = random.randint(1, 30)  # Supondo que você tenha pelo menos 30 residentes
        id_residencia = random.randint(1, 7)  # Supondo que você tenha pelo menos 7 residências
        status = random.choice(['Ativo', 'Concluído', 'Desligado'])
        data_inicio = fake.date_between(start_date='-2y', end_date='today')
        data_conclusao = fake.date_between(start_date=data_inicio, end_date='+1y') if status != 'Desligado' else None
        data_desligamento = fake.date_between(start_date=data_inicio,
                                              end_date=data_conclusao) if status == 'Desligado' else None

        cur.execute("""
            INSERT INTO public.matriculas (id_residente, id_residencia, status_matricula, data_inicio_matricula, 
            data_conclusao_prevista_matricula, data_desligamento_matricula)
            VALUES (%s, %s, %s, %s, %s, %s)
        """, (id_residente, id_residencia, status, data_inicio, data_conclusao, data_desligamento))

    print(f"{n} matrículas inseridas.")


# Chamar as funções para gerar dados fictícios
# gerar_residencias(7)  # Gera 7 residências
gerar_residentes(30)  # Gera 30 residentes
# gerar_matriculas(30)  # Gera 30 matrículas

# Efetivar as alterações no banco de dados
conn.commit()

# Fechar a conexão
cur.close()
conn.close()
