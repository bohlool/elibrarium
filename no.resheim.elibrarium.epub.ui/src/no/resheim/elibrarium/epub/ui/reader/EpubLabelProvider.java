/*******************************************************************************
 * Copyright (c) 2011, 2012 Torkild U. Resheim.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Torkild U. Resheim - initial API and implementation
 *******************************************************************************/
package no.resheim.elibrarium.epub.ui.reader;

import no.resheim.elibrarium.library.Bookmark;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil.FeatureEList;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.mylyn.docs.epub.ncx.NavPoint;

/**
 * Use to provide labels for EPUB table of contents.
 * 
 * @author Torkild U. Resheim
 */
public class EpubLabelProvider extends LabelProvider {

	private static final EStructuralFeature TEXT = XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text();

	@SuppressWarnings("rawtypes")
	@Override
	public String getText(Object element) {
		if (element instanceof NavPoint) {
			FeatureMap fm = ((NavPoint) element).getNavLabels().get(0).getText().getMixed();
			Object o = fm.get(TEXT, false);
			if (o instanceof FeatureEList) {
				if (((FeatureEList) o).size() > 0) {
					return ((FeatureEList) o).get(0).toString();
				}
			}
		} else if (element instanceof Bookmark) {
			Bookmark note = ((Bookmark) element);
			String text = note.getText();
			if (text == null) {
				return "<missing text>";
			}
			if (text.length() > 140) {
				text = text.substring(0, 140) + "...";
			}
			return text;
		}

		return super.getText(element);
	}

}
