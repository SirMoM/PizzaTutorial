package pizzaTutorial;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Deklaration hilfreicher Methoden zur Erstellung von Ontologien mithilfe der
 * OWL API.
 * 
 * @author Dr. Thomas Farrenkopf
 */
public abstract class AbstractPizzeria {

	protected OWLOntologyManager ontoManager;
	protected IRI iri;
	protected String iriString;
	protected File file;
	protected PrefixManager pm;
	protected OWLDataFactory df;
	protected OWLOntology ontology;

	abstract void createPizzaOntology();

	abstract OWLClass createOWLClass(String name);

	abstract OWLClass createOWLClass(String name, OWLClassExpression superClass);

	abstract OWLObjectProperty createObjProperty(String name);

	abstract OWLObjectProperty createObjProperty(String name,
			OWLObjectPropertyExpression superObjProperty);

	abstract OWLObjectProperty createObjPropertyWithDomainRange(String name,
			OWLObjectPropertyExpression superObjProperty,
			OWLClassExpression domain, OWLClassExpression range);

	abstract void makeObjPropertiesInverse(
			OWLObjectPropertyExpression objPropertyA,
			OWLObjectPropertyExpression objPropertyB);

	abstract void makeObjPropertyTransitive(
			OWLObjectPropertyExpression objProperty);

	abstract void makeObjPropertyFunctional(
			OWLObjectPropertyExpression objProperty);

	abstract void createObjectSomeValuesFromRestriction(OWLClass subClass,
			OWLObjectProperty objProperty, HashSet<OWLClassExpression> fillers);

	abstract void createObjectSomeValuesFromRestriction(OWLClass subClass,
			OWLObjectProperty objProperty, OWLClassExpression... objects);

	abstract void createObjectSomeValuesFromRestriction(OWLClass subClass,
			OWLObjectProperty objProperty, OWLClassExpression object);

	abstract void convertToDefinedClass(OWLClass primitiveClass);

	abstract void setSuperClass(OWLClassExpression subClass,
			OWLClassExpression superClass);

	abstract OWLDataProperty createDataProperty(String name);

	abstract OWLDataProperty createDataProperty(String name,
			OWLDataProperty superDataProperty);

	abstract OWLDataProperty createDataPropertyWithDomainRange(String name,
			OWLDataProperty superDataProperty, OWLClassExpression domain,
			OWLDataRange range);

	abstract OWLNamedIndividual createOWLNamedIndividual(String individualName,
			OWLClassExpression assignedClass);

	abstract OWLNamedIndividual createOWLNamedIndividual(String individualName);

	abstract void createObjectPropertyDomain(OWLObjectProperty property,
			OWLClassExpression domain);

	abstract void createObjectPropertyRange(OWLObjectProperty property,
			OWLClassExpression range);

	abstract void declareEquivalentClasses(OWLClassExpression classA,
			OWLClassExpression classB);

	abstract void makeClassesDisjoint(OWLClass classA, OWLClass classB);

	abstract void makeClassesDisjoint(HashSet<OWLClass> classes);

	abstract void makeClassesDisjoint(OWLClass... classes);

	abstract void makeIndividualsDifferent(OWLIndividual indivA,
			OWLIndividual indivB);

	abstract void makeIndividualsDifferent(HashSet<OWLIndividual> indivs);

	abstract void makeIndividualsDifferent(OWLIndividual... indivs);

	abstract void assignObjectValuesToIndiv(
			OWLObjectPropertyExpression property, OWLIndividual individual,
			OWLIndividual object);

	abstract void createRule(Set<SWRLAtom> body, Set<SWRLAtom> head);

	abstract void assignDataValuesOfTypeInt(OWLDataPropertyExpression property,
			OWLIndividual individual, int value);

	abstract OWLReasoner createReasoner(String name);

}
