package jp.co.akkodis;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
* Servlet implementation class FileUpload
*/
@WebServlet("/upload_file")
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
		@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("message", "画像アップロードテスト");
		RequestDispatcher rd = request.getRequestDispatcher("fileUpload.jsp");
		rd.forward(request, response);
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//inputタグのname属性値を指定して、Partオブジェクトを取得
		Part part = request.getPart("imgFile");

		//アップロードするファイルの名前を取得
		String filename = part.getSubmittedFileName();

		//実際にファイル名確認
		System.out.println(filename);

		//アップロードするパス指定
		String path = getServletContext().getRealPath("/upload");

		//実際にファイルが保存されるパス確認
		System.out.println(path);

		//書き込み
		part.write(path + File.separator + filename);

		//アップロードしたファイルを表示する設定
		request.setAttribute("message", "画像アップしました。ご確認ください。");
		request.setAttribute("img", filename);

		RequestDispatcher rd = request.getRequestDispatcher("fileUpload.jsp");
		rd.forward(request, response);
	}
}