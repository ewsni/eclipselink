/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.persistence.jpa.internal.jpql.parser;

import org.eclipse.persistence.jpa.internal.jpql.WordParser;

/**
 * The second and third arguments of the <b>SUBSTRING</b> function denote the starting position and
 * length of the substring to be returned. These arguments are integers. The first position of a
 * string is denoted by 1. The <b>SUBSTRING</b> function returns a string.
 * <p>
 * JPA 1.0:
 * <div nowrap><b>BNF</b> ::= SUBSTRING(string_primary, simple_arithmetic_expression, simple_arithmetic_expression)<p>
 * JPA 2.0:
 * <div nowrap><b>BNF</b> ::= SUBSTRING(string_primary, simple_arithmetic_expression [, simple_arithmetic_expression])<p>
 * <p>
 * <div nowrap>Example: <b>UPDATE</b> Employee e <b>SET</b> e.firstName = <b>SUBSTRING</b>('TopLink Workbench', 1, 8)<p>
 *
 * @version 2.3
 * @since 2.3
 * @author Pascal Filion
 */
public final class SubstringExpression extends AbstractTripleEncapsulatedExpression {

	/**
	 * Creates a new <code>SubstringExpression</code>.
	 *
	 * @param parent The parent of this expression
	 */
	SubstringExpression(AbstractExpression parent) {
		super(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPQLQueryBNF getQueryBNF() {
		return queryBNF(FunctionsReturningStringsBNF.ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean isThirdExpressionOptional() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPQLQueryBNF parameterExpressionBNF(int index) {
		switch (index) {
			case 1:  return queryBNF(StringPrimaryBNF.ID);
			default: return queryBNF(SimpleArithmeticExpressionBNF.ID);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	String parseIdentifier(WordParser wordParser) {
		return SUBSTRING;
	}
}