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
		HashSet<OWLClass> allClasses = new HashSet<OWLClass>();
		
		OWLClass pizza = createOWLClass("Pizza");
		OWLClass pizzaTopping = createOWLClass("PizzaTopping");
		OWLClass pizzaBase = createOWLClass("PizzaBase");

		allClasses.add(pizza);
		allClasses.add(pizzaTopping);
		allClasses.add(pizzaBase);
		
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLClass namedPizza = createOWLClass("NamedPizza", pizza);
		OWLClass cheesyPizza = createOWLClass("CheesyPizza", pizza);
		OWLClass vegetablePizza = createOWLClass("VegetablePizza", pizza);
//		allClasses.add(namedPizza);
//		allClasses.add(cheesyPizza);
//		allClasses.add(vegetablePizza);
//		makeClassesDisjoint(allClasses);
//		allClasses.clear();
		
		OWLClass margheritaPizza = createOWLClass("MargheritaPizza", namedPizza);
		OWLClass hotAmericanPizza = createOWLClass("HotAmericanPizza", namedPizza);
		allClasses.add(margheritaPizza);
		allClasses.add(hotAmericanPizza);
		makeClassesDisjoint(allClasses);
		allClasses.clear();

		OWLClass americanPizza = createOWLClass("AmericanPizza", margheritaPizza);
		OWLClass sohoPizza = createOWLClass("SohoPizza", margheritaPizza);
		allClasses.add(americanPizza);
		allClasses.add(sohoPizza);
		makeClassesDisjoint(allClasses);
		allClasses.clear();

		OWLClass cheeseTopping = createOWLClass("CheeseTopping", pizzaTopping);
		OWLClass meatToppinig = createOWLClass("MeatTopping", pizzaTopping);
		OWLClass vegetableTopping = createOWLClass("VegetableTopping", pizzaTopping);
		OWLClass seafoodTopping = createOWLClass("SeafoodTopping", pizzaTopping);
		allClasses.add(cheeseTopping);
		allClasses.add(meatToppinig);
		allClasses.add(vegetableTopping);
		allClasses.add(seafoodTopping);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLClass pepperoniTopping = createOWLClass("PepperoniTopping", meatToppinig);
		OWLClass spicyBeefTopping = createOWLClass("SpicyBeefTopping", meatToppinig);
		OWLClass salamiTopping = createOWLClass("SalamiTopping", meatToppinig);
		OWLClass hamTopping = createOWLClass("HamTopping", meatToppinig);
		allClasses.add(pepperoniTopping);
		allClasses.add(spicyBeefTopping);
		allClasses.add(salamiTopping);
		allClasses.add(hamTopping);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLClass tomatoTopping = createOWLClass("TomatoTopping", vegetableTopping);
		OWLClass jalapenoPepperTopping = createOWLClass("JalapenoPepperTopping", vegetableTopping);
		OWLClass oliveTopping = createOWLClass("OliveTopping", vegetableTopping);
		OWLClass mushroomTopping = createOWLClass("MushroomTopping", vegetableTopping);
		OWLClass onionTopping = createOWLClass("OnionTopping", vegetableTopping);
		OWLClass caperTopping = createOWLClass("CaperTopping", vegetableTopping);
		OWLClass pepperTopping = createOWLClass("PepperTopping", vegetableTopping);
		allClasses.add(tomatoTopping);
		allClasses.add(jalapenoPepperTopping);
		allClasses.add(oliveTopping);
		allClasses.add(mushroomTopping);
		allClasses.add(onionTopping);
		allClasses.add(caperTopping);
		allClasses.add(pepperTopping);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLClass redPepperTopping = createOWLClass("RedPepperTopping", pepperTopping);
		OWLClass greenPepperTopping = createOWLClass("GreenPepperTopping", pepperTopping);
		allClasses.add(redPepperTopping);
		allClasses.add(greenPepperTopping);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLClass mozzarellaTopping = createOWLClass("MozzarellaTopping", cheeseTopping);
		OWLClass parmezanTopping = createOWLClass("ParmezanTopping", cheeseTopping);
		allClasses.add(mozzarellaTopping);
		allClasses.add(parmezanTopping);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		
		OWLClass tunaTopping = createOWLClass("TunaTopping", seafoodTopping);
		OWLClass anchovyTopping = createOWLClass("AnchovyTopping", seafoodTopping);
		OWLClass prawnTopping = createOWLClass("PrawnTopping", seafoodTopping);
		allClasses.add(tunaTopping);
		allClasses.add(anchovyTopping);
		allClasses.add(prawnTopping);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLClass thinAndCrispyBase = createOWLClass("ThinAndCrispyBase", pizzaBase);
		OWLClass deepPanBase = createOWLClass("DeepPanBase", pizzaBase);
		allClasses.add(thinAndCrispyBase);
		allClasses.add(deepPanBase);
		makeClassesDisjoint(allClasses);
		allClasses.clear();
		
		OWLObjectProperty hasIngredient = createObjProperty("hasIngredient");
		makeObjPropertyTransitive(hasIngredient);

		OWLObjectProperty hasTopping = createObjProperty("hasTopping", hasIngredient);
		createObjectPropertyDomain(hasTopping, pizza);
		createObjectPropertyRange(hasTopping, pizzaTopping);
		
		
		OWLObjectProperty isIngredientOf = createObjProperty("isIngredientOf");
		makeObjPropertiesInverse(hasIngredient, isIngredientOf);
		
		OWLObjectProperty isToppingOf = createObjProperty("isToppingOf", isIngredientOf);
		createObjectPropertyDomain(isToppingOf, pizzaTopping);
		createObjectPropertyRange(isToppingOf, pizza);
		makeObjPropertiesInverse(hasTopping, isToppingOf);
		
		OWLObjectProperty hasBase = createObjProperty("hasBase", hasIngredient);
		makeObjPropertyFunctional(hasBase);
		createObjectPropertyDomain(hasBase, pizza);
		createObjectPropertyRange(hasBase, pizzaBase);
		
	
		OWLObjectProperty isBaseOf = createObjProperty("isBaseOf", isIngredientOf);
		createObjectPropertyRange(isBaseOf, pizza);
		createObjectPropertyDomain(isBaseOf, pizzaBase);
		makeObjPropertiesInverse(hasBase, isBaseOf);
		
		createObjectSomeValuesFromRestriction(margheritaPizza, hasTopping, tomatoTopping);
		createObjectSomeValuesFromRestriction(margheritaPizza, hasTopping, mozzarellaTopping);
		
		createObjectSomeValuesFromRestriction(hotAmericanPizza, hasTopping, tomatoTopping);
		createObjectSomeValuesFromRestriction(hotAmericanPizza, hasTopping, mozzarellaTopping);
		createObjectSomeValuesFromRestriction(hotAmericanPizza, hasTopping, pepperoniTopping);
		createObjectSomeValuesFromRestriction(hotAmericanPizza, hasTopping, jalapenoPepperTopping);
		
		createObjectSomeValuesFromRestriction(pizza, hasBase, pizzaBase);

		createObjectSomeValuesFromRestriction(americanPizza, hasTopping, salamiTopping);
		createObjectSomeValuesFromRestriction(sohoPizza, hasTopping, oliveTopping);
		createObjectSomeValuesFromRestriction(sohoPizza, hasTopping, parmezanTopping);
		
		createObjectSomeValuesFromRestriction(cheesyPizza, hasTopping, cheeseTopping);
		
		OWLObjectUnionOf ouo = df.getOWLObjectUnionOf(tomatoTopping, mozzarellaTopping);
		
		HashSet<OWLClass> classes = new HashSet<OWLClass>();
		classes.add(tomatoTopping);
		classes.add(mozzarellaTopping);
		createObjectAllValuesFromRestriction(vegetablePizza, hasTopping, classes);
		
		convertToDefinedClass(cheesyPizza);
		convertToDefinedClass(vegetablePizza);
		
		
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
	
	private void createObjectAllValuesFromRestriction(OWLClass subClass, OWLObjectProperty objProperty,
			OWLObjectUnionOf ouo) {
		OWLClassExpression anonymousClass = df.getOWLObjectAllValuesFrom(objProperty, ouo);
		OWLSubClassOfAxiom sub = df.getOWLSubClassOfAxiom(subClass, anonymousClass);
		ontoManager.addAxiom(ontology, sub);
	}

	void createObjectAllValuesFromRestriction(OWLClass subClass, OWLObjectProperty objProperty, Set<OWLClass> classes) {
		OWLObjectUnionOf ouo = df.getOWLObjectUnionOf(classes);
		OWLClassExpression anonymousClass = df.getOWLObjectAllValuesFrom(objProperty, ouo);
		OWLSubClassOfAxiom sub = df.getOWLSubClassOfAxiom(subClass, anonymousClass);
		ontoManager.addAxiom(ontology, sub);
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
