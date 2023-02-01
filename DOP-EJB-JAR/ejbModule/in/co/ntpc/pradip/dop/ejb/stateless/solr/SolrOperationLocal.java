package in.co.ntpc.pradip.dop.ejb.stateless.solr;

import in.co.ntpc.pradip.ejb.model.CustomSearchResult;
import in.co.ntpc.pradip.ejb.model.DOPMeataDataSchema;
import in.co.ntpc.pradip.ejb.model.DOPSchema;

//import java.nio.ByteBuffer;
import java.util.List;

import javax.ejb.Local;


@Local
public interface SolrOperationLocal {

	public List<String> getDopSection();
	public List<String> getClauseBySection(String section);
	public List<String> getSubClauseByClauseAndSection(String clause,String section);
	public List<String> getRomanClauseBySubClauseAndClauseAndSection(String subclause,String clause,String section);

	public List<CustomSearchResult> getResultBasedOnMetaData(String section,String clause,String subclause,String romanclause);
	
	
	//----

	public List<CustomSearchResult> solrSearchResultWithHighlight(String page_text_content);

	public byte[] getPageImageByPageId(String doc_id);
	public byte[] getPageImageByPageNo(String page_no);

	public DOPSchema getCurrentPage(String page_no);
	public DOPSchema getNextPage(String page_no);
	public DOPSchema getPreviousPage (String page_no);
	public String getTotalPages();
	
	public List<DOPMeataDataSchema> getCurrentPageMetaData(String page_no);
	
	
}
