package com.test2;

// Generated from D:\test\anlter\a1.g4 by ANTLR 4.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class a1Parser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, VAR=8, INT=9, 
		STRING=10, WS=11;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'+'", "'-'", "'*'", "'('", "'='", "';'", "VAR", "INT", 
		"STRING", "WS"
	};
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_expression = 2, RULE_multExpr = 3, 
		RULE_atom = 4;
	public static final String[] ruleNames = {
		"program", "statement", "expression", "multExpr", "atom"
	};

	@Override
	public String getGrammarFileName() { return "a1.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public a1Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10); statement();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << VAR) | (1L << INT) | (1L << STRING))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode VAR() { return getToken(a1Parser.VAR, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			switch (_input.LA(1)) {
			case 5:
			case INT:
			case STRING:
				{
				setState(15); expression();
				}
				break;
			case VAR:
				{
				setState(16); match(VAR);
				setState(17); match(6);
				setState(18); expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(21); match(7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<MultExprContext> multExpr() {
			return getRuleContexts(MultExprContext.class);
		}
		public TerminalNode STRING() { return getToken(a1Parser.STRING, 0); }
		public MultExprContext multExpr(int i) {
			return getRuleContext(MultExprContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		int _la;
		try {
			setState(32);
			switch (_input.LA(1)) {
			case 5:
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(23); multExpr();
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==2 || _la==3) {
					{
					{
					setState(24);
					_la = _input.LA(1);
					if ( !(_la==2 || _la==3) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(25); multExpr();
					}
					}
					setState(30);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(31); match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultExprContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public MultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multExpr; }
	}

	public final MultExprContext multExpr() throws RecognitionException {
		MultExprContext _localctx = new MultExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); atom();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(35); match(4);
				setState(36); atom();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INT() { return getToken(a1Parser.INT, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atom);
		try {
			setState(47);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(42); match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 2);
				{
				setState(43); match(5);
				setState(44); expression();
				setState(45); match(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\r\64\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16"+
		"\2\17\3\3\3\3\3\3\3\3\5\3\26\n\3\3\3\3\3\3\4\3\4\3\4\7\4\35\n\4\f\4\16"+
		"\4 \13\4\3\4\5\4#\n\4\3\5\3\5\3\5\7\5(\n\5\f\5\16\5+\13\5\3\6\3\6\3\6"+
		"\3\6\3\6\5\6\62\n\6\3\6\2\7\2\4\6\b\n\2\3\3\4\5\64\2\r\3\2\2\2\4\25\3"+
		"\2\2\2\6\"\3\2\2\2\b$\3\2\2\2\n\61\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16"+
		"\17\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\26\5\6\4\2\22\23"+
		"\7\n\2\2\23\24\7\b\2\2\24\26\5\6\4\2\25\21\3\2\2\2\25\22\3\2\2\2\26\27"+
		"\3\2\2\2\27\30\7\t\2\2\30\5\3\2\2\2\31\36\5\b\5\2\32\33\t\2\2\2\33\35"+
		"\5\b\5\2\34\32\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37#\3\2"+
		"\2\2 \36\3\2\2\2!#\7\f\2\2\"\31\3\2\2\2\"!\3\2\2\2#\7\3\2\2\2$)\5\n\6"+
		"\2%&\7\6\2\2&(\5\n\6\2\'%\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\t\3"+
		"\2\2\2+)\3\2\2\2,\62\7\13\2\2-.\7\7\2\2./\5\6\4\2/\60\7\3\2\2\60\62\3"+
		"\2\2\2\61,\3\2\2\2\61-\3\2\2\2\62\13\3\2\2\2\b\17\25\36\")\61";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}