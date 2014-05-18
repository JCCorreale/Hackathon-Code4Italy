package it.camera.hackathon.textmining.stemming;

public class ItalianStemmer extends Stemmer
{
	private final static ItalianStemmer methodObject = new ItalianStemmer ();

    private final static StemmerAmong a_0[] = 
    {
        new StemmerAmong ( "", -1, 7, "", methodObject ),
        new StemmerAmong ( "qu", 0, 6, "", methodObject ),
        new StemmerAmong ( "\u00E1", 0, 1, "", methodObject ),
        new StemmerAmong ( "\u00E9", 0, 2, "", methodObject ),
        new StemmerAmong ( "\u00ED", 0, 3, "", methodObject ),
        new StemmerAmong ( "\u00F3", 0, 4, "", methodObject ),
        new StemmerAmong ( "\u00FA", 0, 5, "", methodObject )
    };

    private final static StemmerAmong a_1[] = 
    {
        new StemmerAmong ( "", -1, 3, "", methodObject ),
        new StemmerAmong ( "I", 0, 1, "", methodObject ),
        new StemmerAmong ( "U", 0, 2, "", methodObject )
    };

    private final static StemmerAmong a_2[] = 
    {
        new StemmerAmong ( "la", -1, -1, "", methodObject ),
        new StemmerAmong ( "cela", 0, -1, "", methodObject ),
        new StemmerAmong ( "gliela", 0, -1, "", methodObject ),
        new StemmerAmong ( "mela", 0, -1, "", methodObject ),
        new StemmerAmong ( "tela", 0, -1, "", methodObject ),
        new StemmerAmong ( "vela", 0, -1, "", methodObject ),
        new StemmerAmong ( "le", -1, -1, "", methodObject ),
        new StemmerAmong ( "cele", 6, -1, "", methodObject ),
        new StemmerAmong ( "gliele", 6, -1, "", methodObject ),
        new StemmerAmong ( "mele", 6, -1, "", methodObject ),
        new StemmerAmong ( "tele", 6, -1, "", methodObject ),
        new StemmerAmong ( "vele", 6, -1, "", methodObject ),
        new StemmerAmong ( "ne", -1, -1, "", methodObject ),
        new StemmerAmong ( "cene", 12, -1, "", methodObject ),
        new StemmerAmong ( "gliene", 12, -1, "", methodObject ),
        new StemmerAmong ( "mene", 12, -1, "", methodObject ),
        new StemmerAmong ( "sene", 12, -1, "", methodObject ),
        new StemmerAmong ( "tene", 12, -1, "", methodObject ),
        new StemmerAmong ( "vene", 12, -1, "", methodObject ),
        new StemmerAmong ( "ci", -1, -1, "", methodObject ),
        new StemmerAmong ( "li", -1, -1, "", methodObject ),
        new StemmerAmong ( "celi", 20, -1, "", methodObject ),
        new StemmerAmong ( "glieli", 20, -1, "", methodObject ),
        new StemmerAmong ( "meli", 20, -1, "", methodObject ),
        new StemmerAmong ( "teli", 20, -1, "", methodObject ),
        new StemmerAmong ( "veli", 20, -1, "", methodObject ),
        new StemmerAmong ( "gli", 20, -1, "", methodObject ),
        new StemmerAmong ( "mi", -1, -1, "", methodObject ),
        new StemmerAmong ( "si", -1, -1, "", methodObject ),
        new StemmerAmong ( "ti", -1, -1, "", methodObject ),
        new StemmerAmong ( "vi", -1, -1, "", methodObject ),
        new StemmerAmong ( "lo", -1, -1, "", methodObject ),
        new StemmerAmong ( "celo", 31, -1, "", methodObject ),
        new StemmerAmong ( "glielo", 31, -1, "", methodObject ),
        new StemmerAmong ( "melo", 31, -1, "", methodObject ),
        new StemmerAmong ( "telo", 31, -1, "", methodObject ),
        new StemmerAmong ( "velo", 31, -1, "", methodObject )
    };

    private final static StemmerAmong a_3[] =
    {
        new StemmerAmong ( "ando", -1, 1, "", methodObject ),
        new StemmerAmong ( "endo", -1, 1, "", methodObject ),
        new StemmerAmong ( "ar", -1, 2, "", methodObject ),
        new StemmerAmong ( "er", -1, 2, "", methodObject ),
        new StemmerAmong ( "ir", -1, 2, "", methodObject )
    };

    private final static StemmerAmong a_4[] =
    {
        new StemmerAmong ( "ic", -1, -1, "", methodObject ),
        new StemmerAmong ( "abil", -1, -1, "", methodObject ),
        new StemmerAmong ( "os", -1, -1, "", methodObject ),
        new StemmerAmong ( "iv", -1, 1, "", methodObject )
    };

    private final static StemmerAmong a_5[] =
    {
        new StemmerAmong ( "ic", -1, 1, "", methodObject ),
        new StemmerAmong ( "abil", -1, 1, "", methodObject ),
        new StemmerAmong ( "iv", -1, 1, "", methodObject )
    };

    private final static StemmerAmong a_6[] =
    {
        new StemmerAmong ( "ica", -1, 1, "", methodObject ),
        new StemmerAmong ( "logia", -1, 3, "", methodObject ),
        new StemmerAmong ( "osa", -1, 1, "", methodObject ),
        new StemmerAmong ( "ista", -1, 1, "", methodObject ),
        new StemmerAmong ( "iva", -1, 9, "", methodObject ),
        new StemmerAmong ( "anza", -1, 1, "", methodObject ),
        new StemmerAmong ( "enza", -1, 5, "", methodObject ),
        new StemmerAmong ( "ice", -1, 1, "", methodObject ),
        new StemmerAmong ( "atrice", 7, 1, "", methodObject ),
        new StemmerAmong ( "iche", -1, 1, "", methodObject ),
        new StemmerAmong ( "logie", -1, 3, "", methodObject ),
        new StemmerAmong ( "abile", -1, 1, "", methodObject ),
        new StemmerAmong ( "ibile", -1, 1, "", methodObject ),
        new StemmerAmong ( "usione", -1, 4, "", methodObject ),
        new StemmerAmong ( "azione", -1, 2, "", methodObject ),
        new StemmerAmong ( "uzione", -1, 4, "", methodObject ),
        new StemmerAmong ( "atore", -1, 2, "", methodObject ),
        new StemmerAmong ( "ose", -1, 1, "", methodObject ),
        new StemmerAmong ( "ante", -1, 1, "", methodObject ),
        new StemmerAmong ( "mente", -1, 1, "", methodObject ),
        new StemmerAmong ( "amente", 19, 7, "", methodObject ),
        new StemmerAmong ( "iste", -1, 1, "", methodObject ),
        new StemmerAmong ( "ive", -1, 9, "", methodObject ),
        new StemmerAmong ( "anze", -1, 1, "", methodObject ),
        new StemmerAmong ( "enze", -1, 5, "", methodObject ),
        new StemmerAmong ( "ici", -1, 1, "", methodObject ),
        new StemmerAmong ( "atrici", 25, 1, "", methodObject ),
        new StemmerAmong ( "ichi", -1, 1, "", methodObject ),
        new StemmerAmong ( "abili", -1, 1, "", methodObject ),
        new StemmerAmong ( "ibili", -1, 1, "", methodObject ),
        new StemmerAmong ( "ismi", -1, 1, "", methodObject ),
        new StemmerAmong ( "usioni", -1, 4, "", methodObject ),
        new StemmerAmong ( "azioni", -1, 2, "", methodObject ),
        new StemmerAmong ( "uzioni", -1, 4, "", methodObject ),
        new StemmerAmong ( "atori", -1, 2, "", methodObject ),
        new StemmerAmong ( "osi", -1, 1, "", methodObject ),
        new StemmerAmong ( "anti", -1, 1, "", methodObject ),
        new StemmerAmong ( "amenti", -1, 6, "", methodObject ),
        new StemmerAmong ( "imenti", -1, 6, "", methodObject ),
        new StemmerAmong ( "isti", -1, 1, "", methodObject ),
        new StemmerAmong ( "ivi", -1, 9, "", methodObject ),
        new StemmerAmong ( "ico", -1, 1, "", methodObject ),
        new StemmerAmong ( "ismo", -1, 1, "", methodObject ),
        new StemmerAmong ( "oso", -1, 1, "", methodObject ),
        new StemmerAmong ( "amento", -1, 6, "", methodObject ),
        new StemmerAmong ( "imento", -1, 6, "", methodObject ),
        new StemmerAmong ( "ivo", -1, 9, "", methodObject ),
        new StemmerAmong ( "it\u00E0", -1, 8, "", methodObject ),
        new StemmerAmong ( "ist\u00E0", -1, 1, "", methodObject ),
        new StemmerAmong ( "ist\u00E8", -1, 1, "", methodObject ),
        new StemmerAmong ( "ist\u00EC", -1, 1, "", methodObject )
    };

    private final static StemmerAmong a_7[] = 
    {
        new StemmerAmong ( "isca", -1, 1, "", methodObject ),
        new StemmerAmong ( "enda", -1, 1, "", methodObject ),
        new StemmerAmong ( "ata", -1, 1, "", methodObject ),
        new StemmerAmong ( "ita", -1, 1, "", methodObject ),
        new StemmerAmong ( "uta", -1, 1, "", methodObject ),
        new StemmerAmong ( "ava", -1, 1, "", methodObject ),
        new StemmerAmong ( "eva", -1, 1, "", methodObject ),
        new StemmerAmong ( "iva", -1, 1, "", methodObject ),
        new StemmerAmong ( "erebbe", -1, 1, "", methodObject ),
        new StemmerAmong ( "irebbe", -1, 1, "", methodObject ),
        new StemmerAmong ( "isce", -1, 1, "", methodObject ),
        new StemmerAmong ( "ende", -1, 1, "", methodObject ),
        new StemmerAmong ( "are", -1, 1, "", methodObject ),
        new StemmerAmong ( "ere", -1, 1, "", methodObject ),
        new StemmerAmong ( "ire", -1, 1, "", methodObject ),
        new StemmerAmong ( "asse", -1, 1, "", methodObject ),
        new StemmerAmong ( "ate", -1, 1, "", methodObject ),
        new StemmerAmong ( "avate", 16, 1, "", methodObject ),
        new StemmerAmong ( "evate", 16, 1, "", methodObject ),
        new StemmerAmong ( "ivate", 16, 1, "", methodObject ),
        new StemmerAmong ( "ete", -1, 1, "", methodObject ),
        new StemmerAmong ( "erete", 20, 1, "", methodObject ),
        new StemmerAmong ( "irete", 20, 1, "", methodObject ),
        new StemmerAmong ( "ite", -1, 1, "", methodObject ),
        new StemmerAmong ( "ereste", -1, 1, "", methodObject ),
        new StemmerAmong ( "ireste", -1, 1, "", methodObject ),
        new StemmerAmong ( "ute", -1, 1, "", methodObject ),
        new StemmerAmong ( "erai", -1, 1, "", methodObject ),
        new StemmerAmong ( "irai", -1, 1, "", methodObject ),
        new StemmerAmong ( "isci", -1, 1, "", methodObject ),
        new StemmerAmong ( "endi", -1, 1, "", methodObject ),
        new StemmerAmong ( "erei", -1, 1, "", methodObject ),
        new StemmerAmong ( "irei", -1, 1, "", methodObject ),
        new StemmerAmong ( "assi", -1, 1, "", methodObject ),
        new StemmerAmong ( "ati", -1, 1, "", methodObject ),
        new StemmerAmong ( "iti", -1, 1, "", methodObject ),
        new StemmerAmong ( "eresti", -1, 1, "", methodObject ),
        new StemmerAmong ( "iresti", -1, 1, "", methodObject ),
        new StemmerAmong ( "uti", -1, 1, "", methodObject ),
        new StemmerAmong ( "avi", -1, 1, "", methodObject ),
        new StemmerAmong ( "evi", -1, 1, "", methodObject ),
        new StemmerAmong ( "ivi", -1, 1, "", methodObject ),
        new StemmerAmong ( "isco", -1, 1, "", methodObject ),
        new StemmerAmong ( "ando", -1, 1, "", methodObject ),
        new StemmerAmong ( "endo", -1, 1, "", methodObject ),
        new StemmerAmong ( "Yamo", -1, 1, "", methodObject ),
        new StemmerAmong ( "iamo", -1, 1, "", methodObject ),
        new StemmerAmong ( "avamo", -1, 1, "", methodObject ),
        new StemmerAmong ( "evamo", -1, 1, "", methodObject ),
        new StemmerAmong ( "ivamo", -1, 1, "", methodObject ),
        new StemmerAmong ( "eremo", -1, 1, "", methodObject ),
        new StemmerAmong ( "iremo", -1, 1, "", methodObject ),
        new StemmerAmong ( "assimo", -1, 1, "", methodObject ),
        new StemmerAmong ( "ammo", -1, 1, "", methodObject ),
        new StemmerAmong ( "emmo", -1, 1, "", methodObject ),
        new StemmerAmong ( "eremmo", 54, 1, "", methodObject ),
        new StemmerAmong ( "iremmo", 54, 1, "", methodObject ),
        new StemmerAmong ( "immo", -1, 1, "", methodObject ),
        new StemmerAmong ( "ano", -1, 1, "", methodObject ),
        new StemmerAmong ( "iscano", 58, 1, "", methodObject ),
        new StemmerAmong ( "avano", 58, 1, "", methodObject ),
        new StemmerAmong ( "evano", 58, 1, "", methodObject ),
        new StemmerAmong ( "ivano", 58, 1, "", methodObject ),
        new StemmerAmong ( "eranno", -1, 1, "", methodObject ),
        new StemmerAmong ( "iranno", -1, 1, "", methodObject ),
        new StemmerAmong ( "ono", -1, 1, "", methodObject ),
        new StemmerAmong ( "iscono", 65, 1, "", methodObject ),
        new StemmerAmong ( "arono", 65, 1, "", methodObject ),
        new StemmerAmong ( "erono", 65, 1, "", methodObject ),
        new StemmerAmong ( "irono", 65, 1, "", methodObject ),
        new StemmerAmong ( "erebbero", -1, 1, "", methodObject ),
        new StemmerAmong ( "irebbero", -1, 1, "", methodObject ),
        new StemmerAmong ( "assero", -1, 1, "", methodObject ),
        new StemmerAmong ( "essero", -1, 1, "", methodObject ),
        new StemmerAmong ( "issero", -1, 1, "", methodObject ),
        new StemmerAmong ( "ato", -1, 1, "", methodObject ),
        new StemmerAmong ( "ito", -1, 1, "", methodObject ),
        new StemmerAmong ( "uto", -1, 1, "", methodObject ),
        new StemmerAmong ( "avo", -1, 1, "", methodObject ),
        new StemmerAmong ( "evo", -1, 1, "", methodObject ),
        new StemmerAmong ( "ivo", -1, 1, "", methodObject ),
        new StemmerAmong ( "ar", -1, 1, "", methodObject ),
        new StemmerAmong ( "ir", -1, 1, "", methodObject ),
        new StemmerAmong ( "er\u00E0", -1, 1, "", methodObject ),
        new StemmerAmong ( "ir\u00E0", -1, 1, "", methodObject ),
        new StemmerAmong ( "er\u00F2", -1, 1, "", methodObject ),
        new StemmerAmong ( "ir\u00F2", -1, 1, "", methodObject )
    };

    private static final char g_v[] = {17, 65, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128, 128, 8, 2, 1 };

    private static final char g_AEIO[] = {17, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128, 128, 8, 2 };

    private static final char g_CG[] = {17 };

    private int I_p2;
    private int I_p1;
    private int I_pV;

	private boolean r_prelude()
	{
		int among_var;
		int v_1;
		int v_2;
		int v_3;
		int v_4;
		int v_5;

        v_1 = cursor;

        replab0: while(true)
        {
            v_2 = cursor;
            lab1: do 
            {
                bra = cursor;
                among_var = find_among(a_0, 7);
                if (among_var == 0)
                {
                    break lab1;
                }

                ket = cursor;
                switch(among_var) 
                {
                    case 0:
                        break lab1;
                    case 1:
                        slice_from("\u00E0");
                        break;
                    case 2:
                        slice_from("\u00E8");
                        break;
                    case 3:
                        slice_from("\u00EC");
                        break;
                    case 4:
                        slice_from("\u00F2");
                        break;
                    case 5:
                        slice_from("\u00F9");
                        break;
                    case 6:
                        slice_from("qU");
                        break;
                    case 7:
                        if (cursor >= limit)
                        {
                            break lab1;
                        }
                        cursor++;
                        break;
                }
                continue replab0;
            } while (false);
            cursor = v_2;
            break replab0;
        }
        cursor = v_1;

        replab2: while(true)
        {
            v_3 = cursor;
            lab3: do 
            {
                golab4: while(true)
                {
                    v_4 = cursor;
                    lab5: do 
                    {
                        if (!(in_grouping(g_v, 97, 249)))
                        {
                            break lab5;
                        }
                        bra = cursor;
                        lab6: do 
                        {
                            v_5 = cursor;
                            lab7: do 
                            {
                                if (!(eq_s(1, "u")))
                                {
                                    break lab7;
                                }
                                ket = cursor;
                                if (!(in_grouping(g_v, 97, 249)))
                                {
                                    break lab7;
                                }
                                slice_from("U");
                                break lab6;
                            } while (false);
                            cursor = v_5;
                            if (!(eq_s(1, "i")))
                            {
                                break lab5;
                            }
                            ket = cursor;
                            if (!(in_grouping(g_v, 97, 249)))
                            {
                                break lab5;
                            }
                            slice_from("I");
                        } while (false);
                        cursor = v_4;
                        break golab4;
                    } while (false);
                    cursor = v_4;
                    if (cursor >= limit)
                    {
                        break lab3;
                    }
                    cursor++;
                }
                continue replab2;
            } while (false);
            cursor = v_3;
            break replab2;
        }
        return true;
	}

    private boolean r_mark_regions()
    {
        int v_1;
        int v_2;
        int v_3;
        int v_6;
        int v_8;

        I_pV = limit;
        I_p1 = limit;
        I_p2 = limit;
                   
        v_1 = cursor;
        lab0: do 
        {
            lab1: do 
            {
                v_2 = cursor;
                lab2: do 
                {
                    if (!(in_grouping(g_v, 97, 249)))
                    {
                        break lab2;
                    }
                    lab3: do 
                    {
                        v_3 = cursor;
                        lab4: do 
                        {
                            if (!(out_grouping(g_v, 97, 249)))
                            {
                                break lab4;
                            }
                            golab5: while(true)
                            {
                                lab6: do 
                                {
                                    if (!(in_grouping(g_v, 97, 249)))
                                    {
                                        break lab6;
                                    }
                                    break golab5;
                                } while (false);
                                if (cursor >= limit)
                                {
                                    break lab4;
                                }
                                cursor++;
                            }
                            break lab3;
                        } while (false);
                        cursor = v_3;
                        
                        if (!(in_grouping(g_v, 97, 249)))
                        {
                            break lab2;
                        }

                        golab7: while(true)
                        {
                            lab8: do 
                            {
                                if (!(out_grouping(g_v, 97, 249)))
                                {
                                    break lab8;
                                }
                                break golab7;
                            } while (false);
                            if (cursor >= limit)
                            {
                                break lab2;
                            }
                            cursor++;
                        }
                    } while (false);
                    break lab1;
                } while (false);
                cursor = v_2;

                if (!(out_grouping(g_v, 97, 249)))
                {
                    break lab0;
                }

                lab9: do 
                {
                    v_6 = cursor;
                    lab10: do 
                    {
                        if (!(out_grouping(g_v, 97, 249)))
                        {
                            break lab10;
                        }
                        golab11: while(true)
                        {
                            lab12: do 
                            {
                                if (!(in_grouping(g_v, 97, 249)))
                                {
                                    break lab12;
                                }
                                break golab11;
                            } while (false);
                            if (cursor >= limit)
                            {
                                break lab10;
                            }
                            cursor++;
                        }
                        break lab9;
                    } while (false);
                    cursor = v_6;

                    if (!(in_grouping(g_v, 97, 249)))
                    {
                        break lab0;
                    }

                    if (cursor >= limit)
                    {
                        break lab0;
                    }
                    cursor++;
                } while (false);
            } while (false);

            I_pV = cursor;
        } while (false);
        cursor = v_1;

        v_8 = cursor;
        lab13: do 
        {
            golab14: while(true)
            {
                lab15: do 
                {
                    if (!(in_grouping(g_v, 97, 249)))
                    {
                        break lab15;
                    }
                    break golab14;
                } while (false);
                if (cursor >= limit)
                {
                    break lab13;
                }
                cursor++;
            }

            golab16: while(true)
            {
                lab17: do 
                {
                    if (!(out_grouping(g_v, 97, 249)))
                    {
                        break lab17;
                    }
                    break golab16;
                } while (false);
                if (cursor >= limit)
                {
                    break lab13;
                }
                cursor++;
            }

            I_p1 = cursor;

            golab18: while(true)
            {
                lab19: do 
                {
                    if (!(in_grouping(g_v, 97, 249)))
                    {
                        break lab19;
                    }
                    break golab18;
                } while (false);
                if (cursor >= limit)
                {
                    break lab13;
                }
                cursor++;
            }

            golab20: while(true)
            {
                lab21: do 
                {
                    if (!(out_grouping(g_v, 97, 249)))
                    {
                        break lab21;
                    }
                    break golab20;
                } while (false);
                if (cursor >= limit)
                {
                    break lab13;
                }
                cursor++;
            }
            I_p2 = cursor;
        } while (false);
        cursor = v_8;
        return true;
    }

    private boolean r_postlude()
    {
        int among_var;
        int v_1;

        replab0: while(true)
        {
            v_1 = cursor;
            lab1: do 
            {
                bra = cursor;

                among_var = find_among(a_1, 3);
                if (among_var == 0)
                {
                    break lab1;
                }

                ket = cursor;
                switch(among_var) {
                    case 0:
                        break lab1;
                    case 1:
                        slice_from("i");
                        break;
                    case 2:
                        slice_from("u");
                        break;
                    case 3:
                        if (cursor >= limit)
                        {
                            break lab1;
                        }
                        cursor++;
                        break;
                }
                continue replab0;
            } while (false);
            cursor = v_1;
            break replab0;
        }
        return true;
    }

    private boolean r_RV() 
    {
        if (!(I_pV <= cursor))
        {
            return false;
        }
        return true;
    }

    private boolean r_R1()
    {
        if (!(I_p1 <= cursor))
        {
            return false;
        }
        return true;
    }

    private boolean r_R2()
    {
        if (!(I_p2 <= cursor))
        {
            return false;
        }
        return true;
    }

    private boolean r_attached_pronoun()
    {
		int among_var;
        ket = cursor;
        if (find_among_b(a_2, 37) == 0)
        {
            return false;
        }
        bra = cursor;

        among_var = find_among_b(a_3, 5);
        if (among_var == 0)
        {
            return false;
        }
        if (!r_RV())
        {
            return false;
        }
        switch(among_var) 
        {
            case 0:
                return false;
            case 1:
                slice_del();
                break;
            case 2:
                slice_from("e");
                break;
        }
        return true;
    }

    private boolean r_standard_suffix()
    {
	    int among_var;
	    int v_1;
	    int v_2;
	    int v_3;
	    int v_4;

        ket = cursor;

        among_var = find_among_b(a_6, 51);
        if (among_var == 0)
        {
            return false;
        }

        bra = cursor;
        switch(among_var) {
            case 0:
                return false;
            case 1:
                if (!r_R2())
                {
                    return false;
                }
                slice_del();
                break;
            case 2:
                if (!r_R2())
                {
                    return false;
                }
                slice_del();
                v_1 = limit - cursor;
                lab0: do 
                {
                    ket = cursor;

                    if (!(eq_s_b(2, "ic")))
                    {
                        cursor = limit - v_1;
                        break lab0;
                    }

                    bra = cursor;

                    if (!r_R2())
                    {
                        cursor = limit - v_1;
                        break lab0;
                    }

                    slice_del();
                } while (false);
                break;
            case 3:
                if (!r_R2())
                {
                    return false;
                }
                slice_from("log");
                break;
            case 4:
                if (!r_R2())
                {
                    return false;
                }
                slice_from("u");
                break;
            case 5:
                if (!r_R2())
                {
                    return false;
                }
                slice_from("ente");
                break;
            case 6:
                if (!r_RV())
                {
                    return false;
                }
                slice_del();
                break;
            case 7:
                if (!r_R1())
                {
                    return false;
                }

                slice_del();

                v_2 = limit - cursor;
                lab1: do 
                {
                    ket = cursor;
                    among_var = find_among_b(a_4, 4);
                    if (among_var == 0)
                    {
                        cursor = limit - v_2;
                        break lab1;
                    }
                    bra = cursor;
                    if (!r_R2())
                    {
                        cursor = limit - v_2;
                        break lab1;
                    }
                    slice_del();
                    switch(among_var) {
                        case 0:
                            cursor = limit - v_2;
                            break lab1;
                        case 1:
                            ket = cursor;
                            if (!(eq_s_b(2, "at")))
                            {
                                cursor = limit - v_2;
                                break lab1;
                            }
                            bra = cursor;
                            if (!r_R2())
                            {
                                cursor = limit - v_2;
                                break lab1;
                            }
                            slice_del();
                            break;
                    }
                } while (false);
                break;
            case 8:
                if (!r_R2())
                {
                    return false;
                }
                slice_del();
                v_3 = limit - cursor;
                lab2: do
                {
                    ket = cursor;
                    among_var = find_among_b(a_5, 3);
                    if (among_var == 0)
                    {
                        cursor = limit - v_3;
                        break lab2;
                    }
                    bra = cursor;
                    switch(among_var) 
                    {
                        case 0:
                            cursor = limit - v_3;
                            break lab2;
                        case 1:
                            if (!r_R2())
                            {
                                cursor = limit - v_3;
                                break lab2;
                            }
                            slice_del();
                            break;
                    }
                } while (false);
                break;
            case 9:
                if (!r_R2())
                {
                    return false;
                }
                slice_del();
                v_4 = limit - cursor;
                lab3: do {
                    ket = cursor;
                    if (!(eq_s_b(2, "at")))
                    {
                        cursor = limit - v_4;
                        break lab3;
                    }
                    bra = cursor;
                    if (!r_R2())
                    {
                        cursor = limit - v_4;
                        break lab3;
                    }
                    slice_del();
                    ket = cursor;
                    if (!(eq_s_b(2, "ic")))
                    {
                        cursor = limit - v_4;
                        break lab3;
                    }
                    bra = cursor;
                    if (!r_R2())
                    {
                        cursor = limit - v_4;
                        break lab3;
                    }
                    slice_del();
                } while (false);
                break;
        }
        return true;
    }

    private boolean r_verb_suffix() 
    {
        int among_var;
        int v_1;
        int v_2;

        v_1 = limit - cursor;

        if (cursor < I_pV)
        {
            return false;
        }
        cursor = I_pV;
        v_2 = limit_backward;
        limit_backward = cursor;
        cursor = limit - v_1;
        ket = cursor;

        among_var = find_among_b(a_7, 87);
        if (among_var == 0)
        {
            limit_backward = v_2;
            return false;
        }
        bra = cursor;
        switch(among_var) 
        {
            case 0:
                limit_backward = v_2;
                return false;
            case 1:
                slice_del();
                break;
        }
        limit_backward = v_2;
        return true;
    }

    private boolean r_vowel_suffix()
    {
		int v_1;
		int v_2;
        
        v_1 = limit - cursor;
        lab0: do 
        {
            ket = cursor;
            if (!(in_grouping_b(g_AEIO, 97, 242)))
            {
                cursor = limit - v_1;
                break lab0;
            }
            bra = cursor;
            if (!r_RV())
            {
                cursor = limit - v_1;
                break lab0;
            }
            slice_del();
            ket = cursor;
            if (!(eq_s_b(1, "i")))
            {
                cursor = limit - v_1;
                break lab0;
            }
            bra = cursor;
            if (!r_RV())
            {
                cursor = limit - v_1;
                break lab0;
            }
            slice_del();
        } while (false);
        v_2 = limit - cursor;
        lab1: do 
        {
            ket = cursor;
            if (!(eq_s_b(1, "h")))
            {
                cursor = limit - v_2;
                break lab1;
            }
            bra = cursor;
            if (!(in_grouping_b(g_CG, 99, 103)))
            {
                cursor = limit - v_2;
                break lab1;
            }
            if (!r_RV())
            {
                cursor = limit - v_2;
                break lab1;
            }
            slice_del();
        } while (false);
        return true;
    }

    public boolean stem() 
    {
        int v_1;
        int v_2;
        int v_3;
        int v_4;
        int v_5;
        int v_6;
        int v_7;
                 
        v_1 = cursor;
        lab0: do
        {
            if (!r_prelude())
            {
                break lab0;
            }
        } while (false);
        cursor = v_1;

        v_2 = cursor;
        lab1: do 
        {
            if (!r_mark_regions())
            {
                break lab1;
            }
        } while (false);
        cursor = v_2;

        limit_backward = cursor; cursor = limit;

        v_3 = limit - cursor;
        lab2: do 
        {
            if (!r_attached_pronoun())
            {
                break lab2;
            }
        } while (false);
        cursor = limit - v_3;
        
        v_4 = limit - cursor;
        lab3: do 
        {
            lab4: do 
            {
                v_5 = limit - cursor;
                lab5: do 
                {
                    if (!r_standard_suffix())
                    {
                        break lab5;
                    }
                    break lab4;
                } while (false);
                cursor = limit - v_5;
                if (!r_verb_suffix())
                {
                    break lab3;
                }
            } while (false);
        } while (false);
        cursor = limit - v_4;

        v_6 = limit - cursor;
        lab6: do 
        {
            if (!r_vowel_suffix())
            {
                break lab6;
            }
        } while (false);
        cursor = limit - v_6;
        cursor = limit_backward; 
        v_7 = cursor;
        lab7: do 
        {
            if (!r_postlude())
            {
                break lab7;
            }
        } while (false);
        cursor = v_7;
        return true;
    }

    public boolean equals( Object o )
    {
        return o instanceof ItalianStemmer;
    }

    public int hashCode()
    {
        return ItalianStemmer.class.getName().hashCode();
    }
}

