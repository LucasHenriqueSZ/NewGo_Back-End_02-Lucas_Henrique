package com.newgo.application;

import com.newgo.application.dto.Produto.AtualizaProdutoDto;
import com.newgo.application.dto.Produto.CadastroProdutoDto;
import com.newgo.application.dto.Produto.RespostaProdutoDto;
import com.newgo.application.dto.RespostaExceptionDto;
import com.newgo.application.mappers.Mapper;
import com.newgo.application.util.LocalizadorDeServico;
import com.newgo.application.util.json.ConversorJson;
import com.newgo.domain.produto.Produto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@WebServlet("/produto")
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String hash = req.getParameter("hash");
        try {
            Produto produto = LocalizadorDeServico.consultaProduto().executar(hash);
            RespostaProdutoDto produtoDto = Mapper.parseObject(produto, RespostaProdutoDto.class);

            String json = ConversorJson.converterToJson(produtoDto);

            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(json);
        } catch (Exception e) {
            responderMensagemErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String requestBody = getBodyReqJson(req);

            CadastroProdutoDto cadastroProdutoDto = ConversorJson.converterParaObjeto(
                    requestBody, CadastroProdutoDto.class);

            Produto produto = Mapper.parseObject(cadastroProdutoDto, Produto.class);

            Produto produtoCadastrado = LocalizadorDeServico.cadastrarProduto().executar(produto);

            RespostaProdutoDto respostaProdutoDto = Mapper.parseObject(produtoCadastrado, RespostaProdutoDto.class);

            String json = ConversorJson.converterToJson(respostaProdutoDto);

            resp.setContentType("application/json");
            resp.setStatus(201);
            resp.getWriter().write(json);
        } catch (Exception e) {
            responderMensagemErro(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String hash = req.getParameter("hash");
        try {
            LocalizadorDeServico.deletarProduto().executar(hash);
            resp.setStatus(200);
        } catch (Exception e) {
            responderMensagemErro(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String hash = req.getParameter("hash");
        try {
            String requestBody = getBodyReqJson(req);

            AtualizaProdutoDto atualizaProdutoDto = ConversorJson.converterParaObjeto(
                    requestBody, AtualizaProdutoDto.class);

            Produto produto = Mapper.parseObject(atualizaProdutoDto, Produto.class);

            Produto produtoAtualizado = LocalizadorDeServico.atualizarProduto().executar(hash,produto);

            RespostaProdutoDto respostaProdutoDto = Mapper.parseObject(produtoAtualizado, RespostaProdutoDto.class);

            String json = ConversorJson.converterToJson(respostaProdutoDto);

            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(json);
        } catch (Exception e) {
            responderMensagemErro(resp, e);
        }
    }

    private static String getBodyReqJson(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return new String(requestBody.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    private static void responderMensagemErro(HttpServletResponse resp, Exception e) throws IOException {
        RespostaExceptionDto respostaExceptionDto = new RespostaExceptionDto(e.getMessage(), LocalDateTime.now());
        String json = ConversorJson.converterToJson(respostaExceptionDto);
        resp.setContentType("application/json");
        resp.setStatus(500);
        resp.getWriter().write(json);
    }
}
