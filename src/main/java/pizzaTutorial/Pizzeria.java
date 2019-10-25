package pizzaTutorial;

import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.ConvertSuperClassesToEquivalentClass;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import uk.ac.manchester.cs.jfact.JFactFactory;
import util.OntologyPrinter;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Erstellung der Pizza-Ontologie mithilfe der OWL API im Rahmen der Blockveranstaltung "Wissensbasierte Methoden".
 * 
 * @author Dr. Thomas Farrenkopf
 * @date 25.10.2019
 * 
 */
public class Pizzeria extends AbstractPizzeria {

	public static void main(String[] args) {
		Pizzeria pz = new Pizzeria();
		pz.createPizzaOntology();

	}

	@Override
	void createPizzaOntology() {
		ontoManager = OWLManager.createOWLOntologyManager();
		iriString = "http://www.thm.de/mnd/wbm/pizzaOntology#";
		iri = IRI.create(iriString);
		try {
			ontology = ontoManager.createOntology(iri);
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pm = new DefaultPrefixManager(iriString);
		df = OWLManager.getOWLDataFactory();



		//TODO: Your code goes here...



		file = new File("src/main/resources/pizzaOntology.owl");
		try {
			ontoManager.saveOntology(ontology, new OWLXMLOntologyFormat(), IRI.create(file));
		} catch (OWLOntologyStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OWLReasoner reasoner = createReasoner("HermiT");

		OntologyPrinter ontoPrinter = new OntologyPrinter((DefaultPrefixManager) pm, ontoManager);

		Node<OWLClass> topNode = reasoner.getTopClassNode();
		ontoPrinter.printOntology(topNode, reasoner, 0);

		Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses();
		Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom();

		if (!unsatisfiable.isEmpty()) {
			System.out.println("The following classes are unsatisfiable: ");

			for (OWLClass cls : unsatisfiable) {
				System.out.println(" " + cls.getIRI());
			}

		} else {
			System.out.println("There are no unsatisfiable classes");
		}
	}

	@Override
	OWLClass createOWLClass(String name) {
		OWLClass cls = df.getOWLClass(name, pm);
		OWLDeclarationAxiom decl = df.getOWLDeclarationAxiom(cls);
		ontoManager.addAxiom(ontology, decl);
		return cls;
	}

	@Override
	OWLClass createOWLClass(String name, OWLClassExpression superClass) {
		OWLClass cls = df.getOWLClass(name, pm);
		OWLSubClassOfAxiom sub = df.getOWLSubClassOfAxiom(cls, superClass);
		ontoManager.addAxiom(ontology, sub);
		return cls;
	}

	@Override
	OWLObjectProperty createObjProperty(String name) {
		OWLObjectProperty op = df.getOWLObjectProperty(name, pm);
		ontoManager.addAxiom(ontology, df.getOWLDeclarationAxiom(op));
		return op;
	}

	@Override
	OWLObjectProperty createObjProperty(String name, OWLObjectPropertyExpression superObjProperty) {
		OWLObjectProperty op = df.getOWLObjectProperty(name, pm);
		ontoManager.addAxiom(ontology, df.getOWLSubObjectPropertyOfAxiom(op, superObjProperty));
		return op;
	}

	@Override
	OWLObjectProperty createObjPropertyWithDomainRange(String name, OWLObjectPropertyExpression superObjProperty, OWLClassExpression domain,
			OWLClassExpression range) {
		OWLObjectProperty op = createObjProperty(name, superObjProperty);
		createObjectPropertyDomain(op, domain);
		createObjectPropertyRange(op, range);
		return op;
	}

	@Override
	void makeObjPropertiesInverse(OWLObjectPropertyExpression objPropertyA, OWLObjectPropertyExpression objPropertyB) {

		ontoManager.addAxiom(ontology, df.getOWLInverseObjectPropertiesAxiom(objPropertyA, objPropertyB));
	}

	@Override
	void makeObjPropertyTransitive(OWLObjectPropertyExpression objProperty) {

		ontoManager.addAxiom(ontology, df.getOWLTransitiveObjectPropertyAxiom(objProperty));
	}

	@Override
	void makeObjPropertyFunctional(OWLObjectPropertyExpression objProperty) {

		ontoManager.addAxiom(ontology, df.getOWLFunctionalObjectPropertyAxiom(objProperty));
	}

	@Override
	void createObjectSomeValuesFromRestriction(OWLClass subClass, OWLObjectProperty objProperty, HashSet<OWLClassExpression> fillers) {
		for (OWLClassExpression object : fillers) {
			OWLClassExpression anonymousClass = df.getOWLObjectSomeValuesFrom(objProperty, object);
			OWLSubClassOfAxiom sub = df.getOWLSubClassOfAxiom(subClass, anonymousClass);
			ontoManager.addAxiom(ontology, sub);
		}
	}

	@Override
	void createObjectSomeValuesFromRestriction(OWLClass subClass, OWLObjectProperty objProperty, OWLClassExpression object) {
		OWLClassExpression anonymousClass = df.getOWLObjectSomeValuesFrom(objProperty, object);
		OWLSubClassOfAxiom sub = df.getOWLSubClassOfAxiom(subClass, anonymousClass);
		ontoManager.addAxiom(ontology, sub);
	}

	@Override
	void createObjectSomeValuesFromRestriction(OWLClass subClass, OWLObjectProperty objProperty, OWLClassExpression... objects) {
		for (OWLClassExpression object : objects) {
			createObjectSomeValuesFromRestriction(subClass, objProperty, object);
		}
	}

	@Override
	void convertToDefinedClass(OWLClass primitiveClass) {
		ConvertSuperClassesToEquivalentClass c = new ConvertSuperClassesToEquivalentClass(df, primitiveClass, Collections.singleton(ontology), ontology);
		ontoManager.applyChanges(c.getChanges());
	}

	@Override
	void setSuperClass(OWLClassExpression subClass, OWLClassExpression superClass) {
		ontoManager.addAxiom(ontology, df.getOWLSubClassOfAxiom(subClass, superClass));
	}

	@Override
	OWLDataProperty createDataProperty(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	OWLDataProperty createDataProperty(String name, OWLDataProperty superDataProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	OWLDataProperty createDataPropertyWithDomainRange(String name, OWLDataProperty superDataProperty, OWLClassExpression domain, OWLDataRange range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	OWLNamedIndividual createOWLNamedIndividual(String individualName, OWLClassExpression assignedClass) {
		OWLNamedIndividual ind = df.getOWLNamedIndividual(individualName, pm);
		OWLClassAssertionAxiom ia = df.getOWLClassAssertionAxiom(assignedClass, ind);
		ontoManager.addAxiom(ontology, ia);

		return ind;
	}

	@Override
	OWLNamedIndividual createOWLNamedIndividual(String individualName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void createObjectPropertyDomain(OWLObjectProperty property, OWLClassExpression domain) {

		ontoManager.addAxiom(ontology, df.getOWLObjectPropertyDomainAxiom(property, domain));
	}

	@Override
	void createObjectPropertyRange(OWLObjectProperty property, OWLClassExpression range) {

		ontoManager.addAxiom(ontology, df.getOWLObjectPropertyRangeAxiom(property, range));
	}

	@Override
	void declareEquivalentClasses(OWLClassExpression classA, OWLClassExpression classB) {
		OWLEquivalentClassesAxiom eca = df.getOWLEquivalentClassesAxiom(classA, classB);
		ontoManager.addAxiom(ontology, eca);

	}

	@Override
	void makeClassesDisjoint(OWLClass classA, OWLClass classB) {
		OWLDisjointClassesAxiom dca = df.getOWLDisjointClassesAxiom(classA, classB);
		ontoManager.addAxiom(ontology, dca);
	}

	@Override
	void makeClassesDisjoint(OWLClass... classes) {
		OWLDisjointClassesAxiom dca = df.getOWLDisjointClassesAxiom(classes);
		ontoManager.addAxiom(ontology, dca);
	}

	@Override
	void makeClassesDisjoint(HashSet<OWLClass> classes) {
		OWLDisjointClassesAxiom dca = df.getOWLDisjointClassesAxiom(classes);
		ontoManager.addAxiom(ontology, dca);
	}

	@Override
	void makeIndividualsDifferent(OWLIndividual indivA, OWLIndividual indivB) {
		ontoManager.addAxiom(ontology, df.getOWLDifferentIndividualsAxiom(indivA, indivB));
	}

	@Override
	void makeIndividualsDifferent(OWLIndividual... indivs) {
		ontoManager.addAxiom(ontology, df.getOWLDifferentIndividualsAxiom(indivs));
	}

	@Override
	void makeIndividualsDifferent(HashSet<OWLIndividual> indivs) {
		// TODO Auto-generated method stub

	}

	@Override
	void assignObjectValuesToIndiv(OWLObjectPropertyExpression property, OWLIndividual individual, OWLIndividual object) {
		OWLObjectPropertyAssertionAxiom dpa = df.getOWLObjectPropertyAssertionAxiom(property, individual, object);
		ontoManager.addAxiom(ontology, dpa);

	}

	@Override
	void createRule(Set<SWRLAtom> body, Set<SWRLAtom> head) {

		SWRLRule rule = df.getSWRLRule(body, head);
		ontoManager.addAxiom(ontology, rule);
	}

	@Override
	void assignDataValuesOfTypeInt(OWLDataPropertyExpression property, OWLIndividual individual, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	OWLReasoner createReasoner(String name) {

		OWLReasonerFactory rf;
		switch (name.toLowerCase()) {

		case ("pellet"):
			rf = new PelletReasonerFactory();
			break;
		case ("hermit"):
			rf = new Reasoner.ReasonerFactory();
			break;
		case ("fact++"):
		default:
			rf = new JFactFactory();
		}

		return rf.createReasoner(ontology);
	}

}
