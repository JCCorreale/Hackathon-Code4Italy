package it.camera.hackathon.persistence;

import it.camera.hackathon.Atto;
import it.camera.hackathon.textmining.clustering.ITerm;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONActDescriptorSaver extends BaseWriter<Entry<Atto, List<ITerm>>> {

	/**
	 * A string representing a IRI of the act.
	 */
	public static final String iriKey = "iri";
	/**
	 * The number of the Italian legislature of the act.
	 */
	public static final String legislaturaKey = "legislatura";
	/**
	 * The label of the act.
	 */
	public static final String labelKey = "label";
	/**
	 * An array of references to other act signers.
	 */
	public static final String altroFirmatarioKey = "altro_firmatario";
	/**
	 * The reference to the first signer of the act.
	 */
	public static final String primoFirmatarioKey = "primo_firmatario";
	/**
	 * The date of the act.
	 */
	public static final String dateKey = "date";
	/**
	 * The reference to the cluster this act belongs to.
	 */
	public static final String clusterKey = "cluster";
	/**
	 * An array of terms which are part of the act, sorted by increasing relevance.
	 */
	public static final String termsKey = "terms";
	/**
	 * Ther URL of the act's content.
	 */
	public static final String contentUrlKey = "content_url";
	
	public JSONActDescriptorSaver(Writer writer)
	{
		super(writer);
	}
	
	/**
	 * Return the JSON object corresponding to the given data.
	 * @param entry
	 * @return
	 */
	public static JSONObject getJSONObject(Entry<Atto, List<ITerm>> entry)
	{
		Atto atto = entry.getKey();
		List<ITerm> terms = entry.getValue();

		JSONObject obj = new JSONObject();
		try 
		{
			obj.put(iriKey, atto.getIRI());
//			obj.put(legislaturaKey, atto.getLegislature());
//			obj.put(labelKey, atto.getLabel());
			obj.put(termsKey, JSONCollectionSaver.getJSONArray(terms));
			obj.put(contentUrlKey, atto.getContentUrl());
		} 
		catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		}
		
		return obj;
	}

	@Override
	public void save(Entry<Atto, List<ITerm>> entry) {
		
		try  {
			getJSONObject(entry).write(super.getWriter());
			super.getWriter().flush();
			super.getWriter().close();
		} 
		catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		} 
		catch (IOException e) {
			throw new IllegalStateException("IO Error");
		}
	}
}