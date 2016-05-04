package ar.edu.itba.paw.models;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
		typeAbstract = "*Def",
		typeImmutable = "*"
)

public abstract class UserDef {
	@org.immutables.builder.Builder.Parameter
	public abstract int getId();

	@org.immutables.builder.Builder.Parameter
	public abstract String getUsername();

	@Value.Auxiliary
	@Value.Default
	public String getPassword() {
		return "pass";
	}
}
