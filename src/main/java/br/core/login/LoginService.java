package br.core.login;

/**
 * Serviço responsável pela autenticação e gestão de segurança do usuário.
 * 
 * <h2>Arquitetura de Segurança (Simulação Local)</h2>
 * <p>
 * Este sistema opera em ambiente local (Java Standalone). Para garantir
 * robustez similar a um ambiente bancário real,
 * recomendamos a seguinte stack tecnológica embarcada:
 * </p>
 * <ul>
 * <li><b>Banco de Dados:</b> H2 Database ou Apache Derby (Modo arquivo para
 * persistência).</li>
 * <li><b>Criptografia:</b> BCrypt ou Argon2 (Nunca armazenar senhas em texto
 * plano).</li>
 * <li><b>Sessão:</b> Gerenciamento Stateful em memória ou tabela temporária no
 * banco.</li>
 * </ul>
 */
public interface LoginService {

    /**
     * Realiza o processo de autenticação do usuário.
     * 
     * <h3>Fluxo de Implementação Recomendado:</h3>
     * <ol>
     * <li><b>Busca:</b> Recuperar o usuário do banco de dados através do
     * CPF/Login.</li>
     * <li><b>Verificação de Bloqueio:</b>
     * <ul>
     * <li>Antes de verificar a senha, checar se a conta está bloqueada (coluna
     * <code>data_desbloqueio</code>).</li>
     * <li>Se estiver bloqueada, lançar exceção específica e não permitir
     * tentativas.</li>
     * </ul>
     * </li>
     * <li><b>Validação de Credenciais (Hash):</b>
     * <ul>
     * <li>Recuperar o <i>Salt</i> e o <i>Hash</i> da senha armazenados.</li>
     * <li>Utilizar algoritmo de hash lento (ex: BCrypt) para comparar a senha
     * informada.</li>
     * <li><b>Nota:</b> Jamais comparar Strings de senha com <code>==</code> ou
     * <code>equals()</code> sem tratamento de tempo constante para evitar <i>Timing
     * Attacks</i> (embora bibliotecas de Hash já cuidem disso).</li>
     * </ul>
     * </li>
     * <li><b>Gestão de Tentativas (Brute Force Protection):</b>
     * <ul>
     * <li><b>Sucesso:</b> Zerar o contador de tentativas falhas.</li>
     * <li><b>Falha:</b> Incrementar contador. Se atingir 3 tentativas, definir
     * <code>data_desbloqueio</code> para 30 minutos no futuro.</li>
     * </ul>
     * </li>
     * <li><b>Auditoria:</b> Registrar o evento (Sucesso ou Falha) no
     * <code>AuditLogger</code>.</li>
     * </ol>
     * 
     * @param login CPF ou Identificador do usuário.
     * @param senha Senha em texto plano (recomenda-se tratar como char[]
     *              internamente se possível).
     * @return O token de sessão ou objeto do Usuário autenticado.
     * @throws SecurityException Se as credenciais forem inválidas ou a conta
     *                           estiver bloqueada.
     */
    Object login(String login, String senha);

    /**
     * Encerra a sessão do usuário de forma segura.
     * 
     * <p>
     * Deve invalidar o token de sessão atual e registrar o logout na auditoria.
     * </p>
     * 
     * @param token Token da sessão ativa.
     */
    void logout(String token);
}
