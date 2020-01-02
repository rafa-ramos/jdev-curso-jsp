package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DAOUsuario;

@WebServlet("/salvar-usuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOUsuario daoUsuario = new DAOUsuario();
	String acao = "";

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String id = request.getParameter("id");
			acao = request.getParameter("acao");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.remover(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("edit")) {

				BeanCursoJsp bean = daoUsuario.consultar(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("user", bean);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(password);
			
			String msg = "";
			try {
				
				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login, id)) {
					msg = "Este usuário já existe!";
				}
				
				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login, id)) {
					daoUsuario.salvar(usuario);
					msg = "Usuário cadastrado com sucesso!";
				} else if (id != null && !id.isEmpty()){
					daoUsuario.atualizar(usuario);
					msg = "Usuário atualizado com sucesso!";
				}
				
				request.setAttribute("msg", msg);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastro-usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
