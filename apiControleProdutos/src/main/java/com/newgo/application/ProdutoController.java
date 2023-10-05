package com.newgo.application;

import com.newgo.application.dto.Produto.AtualizaProdutoDto;
import com.newgo.application.dto.Produto.CadastroProdutoDto;
import com.newgo.application.dto.Produto.RespostaProdutoDto;
import com.newgo.application.dto.RespostaExceptionDto;
import com.newgo.application.mappers.Mapper;
import com.newgo.application.util.LocalizadorDeServico;
import com.newgo.application.util.ParserProdutoURL;
import com.newgo.application.util.json.ConversorJson;
import com.newgo.domain.produto.Produto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/produtos/*")
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setContentType("application/json");
            String recurso = req.getRequestURI().substring(req.getContextPath().length());

            ParserProdutoURL parserProdutoURL = LocalizadorDeServico.parserURL(recurso);

            if (!parserProdutoURL.encontrado()) {
                responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
                return;
            }

            if (parserProdutoURL.endpointProdutoEspecifico()) {
                buscarProdutoEspecifico(parserProdutoURL.getHashProduto(), resp);
                return;
            }

            if (parserProdutoURL.endpointProdutos()) {
                buscarProdutos(resp);
                return;
            }

            responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String recurso = req.getRequestURI().substring(req.getContextPath().length());

            ParserProdutoURL parserProdutoURL = LocalizadorDeServico.parserURL(recurso);

            if (!parserProdutoURL.encontrado()) {
                responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
                return;
            }


            if (parserProdutoURL.endpointProdutos()) {
                cadastratProduto(req, resp);
                return;
            }

            responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ParserProdutoURL parserProdutoURL = LocalizadorDeServico.parserURL(req.getRequestURI().substring(req.getContextPath().length()));

            if (!parserProdutoURL.encontrado()) {
                responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
                return;
            }

            if (parserProdutoURL.endpointProdutoEspecifico()) {
                atualizarProduto(req, resp, parserProdutoURL.getHashProduto());
                return;
            }

            if (parserProdutoURL.endpointAlterarStatusProduto()) {
                alterarStatusProduto(parserProdutoURL.getHashProduto(), parserProdutoURL.getStatusProduto(), resp);
                return;
            }

            responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ParserProdutoURL parserProdutoURL = LocalizadorDeServico.parserURL(req.getRequestURI().substring(req.getContextPath().length()));

            if (!parserProdutoURL.encontrado()) {
                responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
                return;
            }

            if (parserProdutoURL.endpointProdutoEspecifico()) {
                deletarProduto(parserProdutoURL, resp);
                return;
            }

            responderMensagemErro(resp, new Exception("Recurso não encontrado"), 404);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 404);
        }
    }

    private void deletarProduto(ParserProdutoURL parserProdutoURL, HttpServletResponse resp) throws SQLException, IOException {
        LocalizadorDeServico.deletarProduto().executar(parserProdutoURL.getHashProduto());
        escreverResposta(resp, null, 200);
    }

    private void buscarProdutos(HttpServletResponse resp) throws IOException {
        try {
            List<Produto> produtos = LocalizadorDeServico.consultarTodosProdutos().executar();

            List<RespostaProdutoDto> produtosDto = Mapper.parseListObjects(produtos, RespostaProdutoDto.class);

            String json = ConversorJson.converterToJson(produtosDto);

            escreverResposta(resp, json, 200);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 400);
        }
    }

    private static void buscarProdutoEspecifico(String hash, HttpServletResponse resp) throws IOException {
        try {
            Produto produto = LocalizadorDeServico.consultaProduto().executar(hash);
            RespostaProdutoDto produtoDto = Mapper.parseObject(produto, RespostaProdutoDto.class);

            String json = ConversorJson.converterToJson(produtoDto);

            escreverResposta(resp, json, 200);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 404);
        }
    }

    private void alterarStatusProduto(String hashProduto, String statusProduto, HttpServletResponse resp) throws IOException {
        try {
            Produto produto = LocalizadorDeServico.alterarStatusProduto().executar(hashProduto, statusProduto);
            RespostaProdutoDto respostaProdutoDto = Mapper.parseObject(produto, RespostaProdutoDto.class);

            String json = ConversorJson.converterToJson(respostaProdutoDto);

            escreverResposta(resp, json, 200);
        } catch (Exception e) {
            responderMensagemErro(resp, e, 404);
        }
    }

    private static void cadastratProduto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String requestBody = getBodyReqJson(req);

        CadastroProdutoDto cadastroProdutoDto = ConversorJson.converterParaObjeto(
                requestBody, CadastroProdutoDto.class);

        Produto produto = Mapper.parseObject(cadastroProdutoDto, Produto.class);

        Produto produtoCadastrado = LocalizadorDeServico.cadastrarProduto().executar(produto);

        RespostaProdutoDto respostaProdutoDto = Mapper.parseObject(produtoCadastrado, RespostaProdutoDto.class);

        String json = ConversorJson.converterToJson(respostaProdutoDto);

        escreverResposta(resp, json, 201);
    }

    private static void atualizarProduto(HttpServletRequest req, HttpServletResponse resp, String hashProduto) throws Exception {
        String requestBody = getBodyReqJson(req);

        AtualizaProdutoDto atualizaProdutoDto = ConversorJson.converterParaObjeto(
                requestBody, AtualizaProdutoDto.class);

        Produto produto = Mapper.parseObject(atualizaProdutoDto, Produto.class);

        Produto produtoAtualizado = LocalizadorDeServico.atualizarProduto().executar(hashProduto, produto);

        RespostaProdutoDto respostaProdutoDto = Mapper.parseObject(produtoAtualizado, RespostaProdutoDto.class);

        String json = ConversorJson.converterToJson(respostaProdutoDto);

        escreverResposta(resp, json, 200);
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

    private static void responderMensagemErro(HttpServletResponse resp, Exception e, int codigo) throws IOException {
        RespostaExceptionDto respostaExceptionDto = new RespostaExceptionDto(e.getMessage(), LocalDateTime.now());
        String json = ConversorJson.converterToJson(respostaExceptionDto);
        resp.setContentType("application/json");
        resp.setStatus(codigo);
        resp.getWriter().write(json);
    }

    private static void escreverResposta(HttpServletResponse resp, String json, int codigo) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(codigo);
        resp.getWriter().write(json);
    }
}
