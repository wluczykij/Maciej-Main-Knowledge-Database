package maciejDB;

public interface BookAttributes {
	public enum Category {
		NotSet,
		Adventures, 
		BusinessAndFinanace, 
		Children, 
		Computers,
		FamilyAndRelationships, 
		Fantasy, 
		Fiction, 
		HealthAndWellBeing, 
		History,
		Horror, 
		MysteryAndSuspense, 
		ReferenceAndLanguages, 
		SciFi};	
		
	public enum Rating {
		NotSet,
		Horrible, 
		Not_Good, 
		Average, 
		Good, 
		Very_Good, 
		Excellent}
}
