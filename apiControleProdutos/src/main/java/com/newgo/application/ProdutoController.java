package com.newgo.application;

import com.newgo.application.dto.Produto.RespostaProdutoDto;
import com.newgo.application.dto.RespostaExceptionDto;
import com.newgo.application.mappers.Mapper;
import com.newgo.application.util.LocalizadorDeServico;
import com.newgo.application.util.json.ConverteParaJson;
import com.newgo.domain.produto.Produto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/produto")
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String hash = req.getParameter("hash");
        try {
            Produto produto = LocalizadorDeServico.consultaProduto().executar(hash);
            RespostaProdutoDto produtoDto = Mapper.parseObject(produto, RespostaProdutoDto.class);

            String json = ConverteParaJson.converter(produtoDto);

            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(json);
        } catch (Exception e) {
            responderMensagemErro(resp, e);
        }
    }

    private static void responderMensagemErro(HttpServletResponse resp, Exception e) throws IOException {
        RespostaExceptionDto respostaExceptionDto = new RespostaExceptionDto(e.getMessage(), LocalDateTime.now());
        String json = ConverteParaJson.converter(respostaExceptionDto);
        resp.setContentType("application/json");
        resp.setStatus(500);
        resp.getWriter().write(json);
    }
}
