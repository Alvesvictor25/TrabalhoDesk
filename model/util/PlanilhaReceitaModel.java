package model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;

import model.vo.Receita;

public class PlanilhaReceitaModel {

	public String gerarPlanilha(List<Receita> listaReceitas, String caminho) {
		HSSFWorkbook planilha = new HSSFWorkbook();
		HSSFSheet abaPlanilha = planilha.createSheet("Dr.Muquirana - Receita");

		int linhaAtual = 0;

		String[] colunasPlanilha = { "Categoria", "Descrição", "Valor", "Data da receita" };
		criarHeader(colunasPlanilha, abaPlanilha, linhaAtual);
		linhaAtual++;
		
		criarLinhasReceitas(listaReceitas, abaPlanilha, linhaAtual);
		
		redimensionarColunas(colunasPlanilha, abaPlanilha);

		return salvarNoDisco(planilha, caminho, ".xlsx");
	}

	private void redimensionarColunas(String[] colunasPlanilha, HSSFSheet abaPlanilha) {
		for (int i = 0; i < colunasPlanilha.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}
	}

	private void criarLinhasReceitas(List<Receita> listaReceitas, HSSFSheet abaPlanilha, int linhaAtual) {

		for (Receita receita : listaReceitas) {
			HSSFRow novaLinha = abaPlanilha.createRow(linhaAtual);

			novaLinha.createCell(0).setCellValue(receita.getCategoria());
			novaLinha.createCell(1).setCellValue(receita.getDescricao());
			novaLinha.createCell(2).setCellValue(receita.getValor());
			novaLinha.createCell(3).setCellValue(String.valueOf(receita.getDataReceita()));

			linhaAtual++;
		}

	}

	private void criarHeader(String[] colunasPlanilha, HSSFSheet abaPlanilha, int linhaAtual) {
		Row headerRow = abaPlanilha.createRow(linhaAtual);

		for (int i = 0; i < colunasPlanilha.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colunasPlanilha[i]);
		}
	}

	private String salvarNoDisco(HSSFWorkbook planilha, String caminho, String extensao) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminho + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminho + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminho + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminho + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
