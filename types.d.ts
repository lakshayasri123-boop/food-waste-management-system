  | BinaryExpression
  | CallExpression
  | ChainExpression
  | Class
  | ConditionalExpression
  | Function
  | ImportExpression
  | LogicalExpression
  | NewExpression
  | ObjectExpression
  | ParenthesizedExpression
  | SequenceExpression
  | TaggedTemplateExpression
  | ThisExpression
  | UnaryExpression
  | UpdateExpression
  | YieldExpression
  | PrivateInExpression
  | JSXElement
  | JSXFragment
  | TSAsExpression
  | TSSatisfiesExpression
  | TSTypeAssertion
  | TSNonNullExpression
  | TSInstantiationExpression
  | V8IntrinsicExpression
  | MemberExpression;
export interface IdentifierName extends Span {
  type: "Identifier";
  decorators?: [];
  name: string;
  optional?: false;
  typeAnnotation?: null;
  parent?: Node;
}
export interface ThisExpression extends Span {
  type: "ThisExpression";
  parent?: Node;
}
export interface ArrayExpression extends Span {
  type: "ArrayExpression";
  elements: Array<ArrayExpressionElement>;
  parent?: Node;
}
export type ArrayExpressionElement = SpreadElement | null | Expression;
export interface ObjectExpression extends Span {
  type: "ObjectExpression";
  properties: Array<ObjectPropertyKind>;
  parent?: Node;
}
export type ObjectPropertyKind = ObjectProperty | SpreadElement;
export interface ObjectProperty extends Span {
  type: "Property";
  kind: PropertyKind;
  key: PropertyKey;
  value: Expression;
  method: boolean;
  shorthand: boolean;
  computed: boolean;
  optional?: false;
  parent?: Node;
}
export type PropertyKey = IdentifierName | PrivateIdentifier | Expression;
export type PropertyKind = "init" | "get" | "set";
export interface TemplateLiteral extends Span {
  type: "TemplateLiteral";
  quasis: Array<TemplateElement>;
  expressions: Array<Expression>;
  parent?: Node;
}
export interface TaggedTemplateExpression extends Span {
  type: "TaggedTemplateExpression";
  tag: Expression;
  typeArguments?: TSTypeParameterInstantiation | null;
  quasi: TemplateLiteral;
  parent?: Node;
}
export interface TemplateElement extends Span {
  type: "TemplateElement";
  value: TemplateElementValue;
  tail: boolean;
  parent?: Node;
}
export interface TemplateElementValue {
  raw: string;
  cooked: string | null;
}
export type MemberExpression =
  | ComputedMemberExpression
  | StaticMemberExpression
  | PrivateFieldExpression;
export interface ComputedMemberExpression extends Span {
  type: "MemberExpression";
  object: Expression;
  property: Expression;
  optional: boolean;
  computed: true;
  parent?: Node;
}
export interface StaticMemberExpression extends Span {
  type: "MemberExpression";
  object: Expression;
  property: IdentifierName;
  optional: boolean;
  computed: false;
  parent?: Node;
}
export interface PrivateFieldExpression extends Span {
  type: "MemberExpression";
  object: Expression;
  property: PrivateIdentifier;
  optional: boolean;
  computed: false;
  parent?: Node;
}
export interface CallExpression extends Span {
  type: "CallExpression";
  callee: Expression;
  typeArguments?: TSTypeParameterInstantiation | null;
  arguments: Array<Argument>;
  optional: boolean;
  parent?: Node;
}
export interface NewExpression extends Span {
  type: "NewExpression";
  callee: Expression;
  typeArguments?: TSTypeParameterInstantiation | null;
  arguments: Array<Argument>;
  parent?: Node;
}
export interface MetaProperty extends Span {
  type: "MetaProperty";
  meta: IdentifierName;
  property: IdentifierName;
  parent?: Node;
}
export interface SpreadElement extends Span {
  type: "SpreadElement";
  argument: Expression;
  parent?: Node;
}
export type Argument = SpreadElement | Expression;
export interface UpdateExpression extends Span {
  type: "UpdateExpression";
  operator: UpdateOperator;
  prefix: boolean;
  argument: SimpleAssignmentTarget;
  parent?: Node;
}
export interface UnaryExpression extends Span {
  type: "UnaryExpression";
  operator: UnaryOperator;
  argument: Expression;
  prefix: true;
  parent?: Node;
}
export interface BinaryExpression extends Span {
  type: "BinaryExpression";
  left: Expression;
  operator: BinaryOperator;
  right: Expression;
  parent?: Node;
}
export interface LogicalExpression extends Span {
  type: "LogicalExpression";
  left: Expression;
  operator: LogicalOperator;
  right: Expression;
  parent?: Node;
}
export interface ConditionalExpression extends Span {
  type: "ConditionalExpression";
  test: Expression;
  consequent: Expression;
  alternate: Expression;
  parent?: Node;
}
export interface AssignmentExpression extends Span {
  type: "AssignmentExpression";
  operator: AssignmentOperator;
  left: AssignmentTarget;
  right: Expression;
  parent?: Node;
}
export type AssignmentTarget = SimpleAssignmentTarget | AssignmentTargetPattern;
export type SimpleAssignmentTarget =
  | IdentifierReference
  | TSAsExpression
  | TSSatisfiesExpression
  | TSNonNullExpression
  | TSTypeAssertion
  | MemberExpression;
export type AssignmentTargetPattern = ArrayAssignmentTarget | ObjectAssignmentTarget;
export interface ArrayAssignmentTarget extends Span {
  type: "ArrayPattern";
  
   decorators?: [];
  elements: Array<AssignmentTargetMaybeDefault | AssignmentTargetRest | null>;
  optional?: false;
  typeAnnotation?: null;
  parent?: Node;
}
export interface ObjectAssignmentTarget extends Span {
  type: "ObjectPattern";
  decorators?: [];
  properties: Array<AssignmentTargetProperty | AssignmentTargetRest>;
  optional?: false;
  typeAnnotation?: null;
  parent?: Node;
}
export interface AssignmentTargetRest extends Span {
  type: "RestElement";
  decorators?: [];
  argument: AssignmentTarget;
  optional?: false;
  typeAnnotation?: null;
  value?: null;
  parent?: Node;
}
export interface Super extends Span {
  type: "Super";
  parent?: Node;
}
export interface AwaitExpression extends Span {
  type: "AwaitExpression";
  argument: Expression;
  parent?: Node;
}
export interface ChainExpression extends Span {
  type: "ChainExpression";
  expression: ChainElement;
  parent?: Node;
}
export type ChainElement = CallExpression | TSNonNullExpression | MemberExpression;
export interface ParenthesizedExpression extends Span {
  type: "ParenthesizedExpression";
  expression: Expression;
  parent?: Node;
}
  | ThrowStatement
  | TryStatement
  | WhileStatement
  | WithStatement
  | Declaration
  | ModuleDeclaration;
export interface Directive extends Span {
  type: "ExpressionStatement";
  expression: StringLiteral;
  directive: string;
  parent?: Node;
}
export interface Hashbang extends Span {
  type: "Hashbang";
  value: string;
  parent?: Node;
}
export interface BlockStatement extends Span {
  type: "BlockStatement";
  body: Array<Statement>;
  parent?: Node;
}
export type Declaration =
  | VariableDeclaration
  | Function
  | Class
  | TSTypeAliasDeclaration
  | TSInterfaceDeclaration
  | TSEnumDeclaration
  | TSModuleDeclaration
  | TSGlobalDeclaration
  | TSImportEqualsDeclaration;
  type: "IfStatement";
  test: Expression;
  consequent: Statement;
  alternate: Statement | null;
  parent?: Node;
}
export interface DoWhileStatement extends Span {
  type: "DoWhileStatement";
  body: Statement;
  test: Expression;
  parent?: Node;
}
export interface WhileStatement extends Span {
  type: "WhileStatement";
  test: Expression;
  body: Statement;
  parent?: Node;
}
export interface ForStatement extends Span {
  type: "ForStatement";
  init: ForStatementInit | null;
  test: Expression | null;
  update: Expression | null;
  body: Statement;
  parent?: Node;
}
export type ForStatementInit = VariableDeclaration | Expression;
export interface ForInStatement extends Span {
  type: "ForInStatement";
  left: ForStatementLeft;
  right: Expression;
  body: Statement;
  parent?: Node;
}
}
export interface WithStatement extends Span {
  type: "WithStatement";
  object: Expression;
  body: Statement;
  parent?: Node;
}
export interface SwitchStatement extends Span {
  type: "SwitchStatement";
  discriminant: Expression;
  cases: Array<SwitchCase>;
  parent?: Node;
}
export interface SwitchCase extends Span {
  type: "SwitchCase";
  test: Expression | null;
  consequent: Array<Statement>;
  parent?: Node;
}
export interface LabeledStatement extends Span {
  type: "LabeledStatement";
  label: LabelIdentifier;
  body: Statement;
  parent?: Node;
}
export interface ThrowStatement extends Span {
  type: "ThrowStatement";
  argument: Expression;
  parent?: Node;
}
export interface TryStatement extends Span {
  type: "TryStatement";
  block: BlockStatement;
  handler: CatchClause | null;
  finalizer: BlockStatement | null;
  parent?: Node;
}
export interface ObjectPattern extends Span {
  type: "ObjectPattern";
  decorators?: [];
  properties: Array<BindingProperty | BindingRestElement>;
  optional?: false;
  typeAnnotation?: TSTypeAnnotation | null;
  parent?: Node;
}
export interface BindingProperty extends Span {
  type: "Property";
  kind: "init";
  key: PropertyKey;
  value: BindingPattern;
  method: false;
  shorthand: boolean;
  computed: boolean;
  optional?: false;
  parent?: Node;
}
export interface ArrayPattern extends Span {
  type: "ArrayPattern";
  decorators?: [];
  elements: Array<BindingPattern | BindingRestElement | null>;
  optional?: false;
  typeAnnotation?: TSTypeAnnotation | null;
  parent?: Node;
}
export interface BindingRestElement extends Span {
  type: "RestElement";
  decorators?: [];
  argument: BindingPattern;
  optional?: false;
  typeAnnotation?: TSTypeAnnotation | null;
  value?: null;
  parent?: Node;
}
export interface ArrowFunctionExpression extends Span {
  type: "ArrowFunctionExpression";
  expression: boolean;
  async: boolean;
  typeParameters?: TSTypeParameterDeclaration | null;
  params: ParamPattern[];
  returnType?: TSTypeAnnotation | null;
  body: FunctionBody | Expression;
  id: null;
  generator: false;
  parent?: Node;
}
export interface YieldExpression extends Span {
  type: "YieldExpression";
  delegate: boolean;
  argument: Expression | null;
  parent?: Node;
}
export interface Class extends Span {
  type: ClassType;
  decorators: Array<Decorator>;
  id: BindingIdentifier | null;
  typeParameters?: TSTypeParameterDeclaration | null;
  superClass: Expression | null;
  superTypeArguments?: TSTypeParameterInstantiation | null;
  implements?: Array<TSClassImplements>;
  body: ClassBody;
  abstract?: boolean;
  declare?: boolean;
  parent?: Node;
}
export type ClassType = "ClassDeclaration" | "ClassExpression";
export interface ClassBody extends Span {
  type: "ClassBody";
  body: Array<ClassElement>;
  parent?: Node;
}
export interface PrivateIdentifier extends Span {
  type: "PrivateIdentifier";
  name: string;
  parent?: Node;
}
export interface StaticBlock extends Span {
  type: "StaticBlock";
  body: Array<Statement>;
  parent?: Node;
}
export type ModuleDeclaration =
  | ImportDeclaration
  | ExportAllDeclaration
  | ExportDefaultDeclaration
  | ExportNamedDeclaration
  | TSExportAssignment
  | TSNamespaceExportDeclaration;
export type AccessorPropertyType = "AccessorProperty" | "TSAbstractAccessorProperty";
export interface AccessorProperty extends Span {
  type: AccessorPropertyType;
  decorators: Array<Decorator>;
  key: PropertyKey;
  typeAnnotation?: TSTypeAnnotation | null;
  value: Expression | null;
  computed: boolean;
  static: boolean;
  override?: boolean;
  definite?: boolean;
  accessibility?: TSAccessibility | null;
  declare?: false;
  optional?: false;
  readonly?: false;
  parent?: Node;
}
export interface ImportNamespaceSpecifier extends Span {
  type: "ImportNamespaceSpecifier";
  local: BindingIdentifier;
  parent?: Node;
}
export interface ImportAttribute extends Span {
  type: "ImportAttribute";
  key: ImportAttributeKey;
  value: StringLiteral;
  parent?: Node;
}
export type ImportAttributeKey = IdentifierName | StringLiteral;
export interface ExportNamedDeclaration extends Span {
  type: "ExportNamedDeclaration";
  declaration: Declaration | null;
  specifiers: Array<ExportSpecifier>;
  source: StringLiteral | null;
  exportKind?: ImportOrExportKind;
  attributes: Array<ImportAttribute>;
  parent?: Node;
}
export interface ExportDefaultDeclaration extends Span {
  type: "ExportDefaultDeclaration";
  declaration: ExportDefaultDeclarationKind;
  exportKind?: "value";
  parent?: Node;
}
export interface ExportAllDeclaration extends Span {
  type: "ExportAllDeclaration";
  exported: ModuleExportName | null;
  source: StringLiteral;
  attributes: Array<ImportAttribute>;
  exportKind?: ImportOrExportKind;
  parent?: Node;
}
  parent?: Node;
}
export interface RegExpLiteral extends Span {
  type: "Literal";
  value: RegExp | null;
  raw: string | null;
  regex: { pattern: string; flags: string };
  parent?: Node;
}
export interface JSXElement extends Span {
  type: "JSXElement";
  openingElement: JSXOpeningElement;
  children: Array<JSXChild>;
  closingElement: JSXClosingElement | null;
  parent?: Node;
}
export interface JSXOpeningElement extends Span {
  type: "JSXOpeningElement";
  name: JSXElementName;
  typeArguments?: TSTypeParameterInstantiation | null;
  attributes: Array<JSXAttributeItem>;
  selfClosing: boolean;
  parent?: Node;
}
export interface JSXClosingElement extends Span {
  type: "JSXClosingElement";
  name: JSXElementName;
  parent?: Node;
}
export interface JSXFragment extends Span {
  type: "JSXFragment";
  openingFragment: JSXOpeningFragment;
  children: Array<JSXChild>;
  closingFragment: JSXClosingFragment;
  parent?: Node;
}



