
# Escopo Completo do Projeto: API de Sistema de Agenda de Consultas Médicas

## 1. Visão Geral

A API de Sistema de Agenda de Consultas Médicas tem como objetivo fornecer um sistema eficiente para o agendamento de consultas médicas, gestão de médicos e pacientes, com funcionalidades de autenticação, autorização e notificações. A API permitirá que médicos e pacientes gerenciem suas agendas de maneira fácil e intuitiva.

---

## 2. Funcionalidades Principais

### 2.1. Cadastro de Médicos
- **Campos**:
  - Nome completo.
  - Especialidade.
  - CRM (registro no Conselho Regional de Medicina).
  - Telefone.
  - E-mail.
- **Operações**:
  - Cadastrar médicos.
  - Atualizar dados de médicos.
  - Deletar médicos.
  - Consultar lista de médicos.

### 2.2. Cadastro de Pacientes
- **Campos**:
  - Nome completo.
  - Data de nascimento.
  - Telefone.
  - E-mail.
  - Endereço (opcional).
- **Operações**:
  - Cadastrar pacientes.
  - Atualizar dados de pacientes.
  - Deletar pacientes.
  - Consultar lista de pacientes.

### 2.3. Agendamento de Consultas
- **Campos**:
  - Data e horário da consulta.
  - Médico selecionado.
  - Paciente.
- **Operações**:
  - Agendar consulta.
  - Cancelar consulta.
  - Consultar consultas agendadas.
  - Verificar disponibilidade de médicos.
  - Confirmar consulta (pode ser feita tanto pelo paciente quanto pelo médico).

### 2.4. Visualização de Consultas
- **Pacientes** podem consultar suas consultas passadas e futuras.
- **Médicos** podem consultar suas consultas programadas.
- **Operações**:
  - Consultar todas as consultas do paciente.
  - Consultar todas as consultas do médico.

### 2.5. Histórico Médico
- **Campos**:
  - Histórico de consultas (data, diagnóstico, observações, etc.).
- **Operações**:
  - Visualizar histórico de consultas passadas de um paciente.
  - Adicionar histórico médico a uma consulta.

### 2.6. Autenticação e Autorização
- **Spring Security** com **JWT** para autenticação e controle de acesso:
  - **Pacientes** podem ver e agendar consultas.
  - **Médicos** podem visualizar e confirmar/agendar consultas.
  - **Administradores** podem cadastrar médicos e pacientes.
- **Operações**:
  - Login de paciente, médico e administrador.
  - Gerenciamento de senhas (recuperação, alteração).

### 2.7. Relatórios e Monitoramento
- **Relatórios** sobre consultas agendadas, realizadas e canceladas.
- **Filtros** por médico, paciente, e status da consulta.
- **Operações**:
  - Gerar relatório de consultas por período.
  - Gerar relatório de consultas por status.

### 2.8. Notificações
- **Notificação de consulta**: Envio de lembretes por e-mail ou SMS para pacientes e médicos, lembrando de consultas futuras.
- **Operações**:
  - Enviar e-mail/SMS para paciente e médico sobre consultas agendadas.

---

## 3. Tecnologias e Ferramentas

- **Spring Boot**: Framework principal para construção da API.
- **Spring Security**: Para autenticação e autorização (usando JWT).
- **JPA/Hibernate**: Para persistência de dados e comunicação com o banco de dados MySQL.
- **MySQL**: Banco de dados relacional para armazenar informações sobre médicos, pacientes e consultas.
- **Spring Mail**: Para envio de e-mails de notificações.
- **Postman/Insomnia**: Para testes da API.
- **Docker**: Para rodar o MySQL em contêiner.

---

## 4. Estrutura do Banco de Dados

### 4.1. Médicos

```java
@Entity
public class MedicosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especialidade;
    private String crm;
    private String telefone;
    private String email;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
```

### 4.2. Pacientes

```java
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
}
```

### 4.3. Consultas

```java
@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private String status;
    @ManyToOne
    private MedicosEntity medico;
    @ManyToOne
    private Paciente paciente;
}
```

---

## 5. Configuração do Banco de Dados (MySQL)

### 5.1. Dependência no `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### 5.2. Configuração no `application.properties`

```properties
spring.application.name=med-agenda
spring.datasource.url=jdbc:mysql://localhost:3306/med_agenda?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.initialization-mode=always
```

---

## 6. Exemplo de Controller (API REST)

```java
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private CreatedMedicosService createdMedicos;

    @PostMapping("/criar")
    public ResponseEntity<ResponseMedicoDto> createMedico(@RequestBody RequestMedicoDto requestMedicoDto) {
        ResponseMedicoDto responseMedicoDto = createdMedicos.execute(requestMedicoDto);
        return ResponseEntity.ok(responseMedicoDto);
    }
}
```

---

## 7. Exemplo de Corpo JSON para Criar Médico

```json
{
  "nome": "Dr. João Silva",
  "especialidade": "Cardiologia",
  "crm": "123456",
  "telefone": "(11) 98765-4321",
  "email": "dr.joao.silva@clinica.com"
}
```

---

## 8. Tratamento de Exceções

### 8.1. Exceção `MedicosExceptions`

```java
public class MedicosExceptions extends RuntimeException {
    private final int errorCode;
    public MedicosExceptions(String msg) {
        super(msg);
        this.errorCode = 400;
    }
    public int getErrorCode() {
        return errorCode;
    }
}
```

### 8.2. Handler de Exceção Global

```java
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MedicosExceptions.class)
    public ResponseEntity<Object> handleMedicosExceptions(MedicosExceptions ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(ex.getMessage(), ex.getErrorCode())
        );
    }

    public static class ErrorResponse {
        private String message;
        private int code;
        public ErrorResponse(String message, int code) {
            this.message = message;
            this.code = code;
        }
        public String getMessage() {
            return message;
        }
        public int getCode() {
            return code;
        }
    }
}
```

---

## 9. Cronograma de Desenvolvimento

### Fase 1: Planejamento e Configuração
- **Semana 1**: Estruturar o banco de dados, criar entidades JPA, configurar o Spring Boot com MySQL.
- **Semana 2**: Configurar Spring Security com JWT e criar endpoints básicos para médico e paciente.

### Fase 2: Implementação de Funcionalidades
- **Semana 3**: Implementar funcionalidades de agendamento de consultas, consultar consultas.
- **Semana 4**: Implementar notificação por e-mail/SMS, e funcionalidades de relatórios e histórico médico.

### Fase 3: Testes e Documentação
- **Semana 5**: Testar a API com Postman/Insomnia, escrever documentação da API (Swagger ou similar), corrigir bugs.

