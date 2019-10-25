package util;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.util.Iterator;
import java.util.Set;

/**
 * This class provides utility to print out a class hierarchy of a full ontology
 * or different parts at console.
 */
public class OntologyPrinter {

	private final DefaultPrefixManager pm;
	private final OWLDataFactory df;
	private OWLReasoner reasoner;

	public OntologyPrinter(DefaultPrefixManager pm,
			OWLOntologyManager ontoManager) {

		df = ontoManager.getOWLDataFactory();
		this.pm = pm;

	}

	/**
	 * Prints out the structure of a whole ontology.
	 * 
	 * @param parent
	 * @param reasoner
	 * @param depth
	 */
	public void printOntology(Node<OWLClass> parent, OWLReasoner reasoner,
			int depth) {

		// Don't want to print out the bottom node
		if (parent.isBottomNode()) {
			return;
		}
		// Print an indent to denote parent-child relationships
		printIndent(depth);
		// Now print the node (containing the child classes)
		printNode(parent);
		for (Node<OWLClass> child : reasoner.getSubClasses(
				parent.getRepresentativeElement(), true)) {
			// Recurse to print the children
			printOntology(child, reasoner, depth + 1);
		}
	}

	/**
	 * Prints out indent of specific size.
	 * 
	 * @param depth
	 */
	private void printIndent(int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print("    ");
		}
	}

	/**
	 * Prints out a node as a list of class names in curly brackets.
	 * 
	 * @param node
	 */
	private void printNode(Node<OWLClass> node) {
		System.out.print("{");
		for (Iterator<OWLClass> it = node.getEntities().iterator(); it
				.hasNext();) {
			OWLClass cls = it.next();
			// Use a prefix manager to provide a shorter name
			System.out.print(pm.getShortForm(cls));
			if (it.hasNext()) {
				System.out.print(" ");
			}
		}
		System.out.println("}");
	}

	/**
	 * Prints out a sub classes of a given top class.
	 * 
	 * @param topClassName
	 */
	public void printOutSubclasses(String topClassName) {

		OWLClass topClass = df.getOWLClass(":" + topClassName, pm);
		NodeSet<OWLClass> subClses = reasoner.getSubClasses(topClass, true);

		Set<OWLClass> clses = subClses.getFlattened();
		System.out.println("Subclasses of: " + topClassName);
		for (OWLClass cls : clses) {
			System.out.println("    " + cls);
		}
		System.out.println("\n");
	}

	/**
	 * Prints out a individuals of a given top class.
	 * 
	 * @param topClassName
	 */
	public void printOutIndividuals(String topClassName) {
		OWLClass topClass = df.getOWLClass(":" + topClassName, pm);
		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(
				topClass, true);

		Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
		System.out.println("Instances of: " + topClassName);
		for (OWLNamedIndividual ind : individuals) {
			System.out.println("    " + ind);
		}
		System.out.println("\n");
	}
}
