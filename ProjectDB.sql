PGDMP     1                    {            creditDB    15.4    15.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16399    creditDB    DATABASE     ~   CREATE DATABASE "creditDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "creditDB";
                manager1    false            �            1259    16431    clients    TABLE     $  CREATE TABLE public.clients (
    id bigint NOT NULL,
    fio text NOT NULL,
    passport text NOT NULL,
    family_status text,
    address text NOT NULL,
    phone_number text NOT NULL,
    job_title text,
    job_organization text,
    job_period_start text,
    job_period_finish text
);
    DROP TABLE public.clients;
       public         heap    postgres    false            �            1259    16466    clients_id_seq    SEQUENCE     �   ALTER TABLE public.clients ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214            �            1259    16452    loan_agreements    TABLE     �   CREATE TABLE public.loan_agreements (
    id bigint NOT NULL,
    application_id bigint NOT NULL,
    signing_date date,
    status text NOT NULL
);
 #   DROP TABLE public.loan_agreements;
       public         heap    postgres    false            �            1259    16468    loan_agreements_id_seq    SEQUENCE     �   ALTER TABLE public.loan_agreements ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.loan_agreements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16440    loan_applications    TABLE     �   CREATE TABLE public.loan_applications (
    id bigint NOT NULL,
    client_id bigint NOT NULL,
    status text NOT NULL,
    period integer NOT NULL,
    sum integer NOT NULL
);
 %   DROP TABLE public.loan_applications;
       public         heap    postgres    false            �            1259    16464    loan_applications_id_seq    SEQUENCE     �   ALTER TABLE public.loan_applications ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.loan_applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215                      0    16431    clients 
   TABLE DATA           �   COPY public.clients (id, fio, passport, family_status, address, phone_number, job_title, job_organization, job_period_start, job_period_finish) FROM stdin;
    public          postgres    false    214   �                 0    16452    loan_agreements 
   TABLE DATA           S   COPY public.loan_agreements (id, application_id, signing_date, status) FROM stdin;
    public          postgres    false    216   �                 0    16440    loan_applications 
   TABLE DATA           O   COPY public.loan_applications (id, client_id, status, period, sum) FROM stdin;
    public          postgres    false    215   �                  0    0    clients_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.clients_id_seq', 16, true);
          public          postgres    false    218                       0    0    loan_agreements_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.loan_agreements_id_seq', 5, true);
          public          postgres    false    219                       0    0    loan_applications_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.loan_applications_id_seq', 27, true);
          public          postgres    false    217            p           2606    16437    clients clients_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.clients DROP CONSTRAINT clients_pkey;
       public            postgres    false    214            t           2606    16458 $   loan_agreements loan_agreements_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.loan_agreements
    ADD CONSTRAINT loan_agreements_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.loan_agreements DROP CONSTRAINT loan_agreements_pkey;
       public            postgres    false    216            r           2606    16446 (   loan_applications loan_applications_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.loan_applications
    ADD CONSTRAINT loan_applications_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.loan_applications DROP CONSTRAINT loan_applications_pkey;
       public            postgres    false    215            v           2606    16459 (   loan_agreements fk_application_agreement    FK CONSTRAINT     �   ALTER TABLE ONLY public.loan_agreements
    ADD CONSTRAINT fk_application_agreement FOREIGN KEY (application_id) REFERENCES public.loan_applications(id);
 R   ALTER TABLE ONLY public.loan_agreements DROP CONSTRAINT fk_application_agreement;
       public          postgres    false    216    3186    215            u           2606    16447 '   loan_applications fk_client_application    FK CONSTRAINT     �   ALTER TABLE ONLY public.loan_applications
    ADD CONSTRAINT fk_client_application FOREIGN KEY (client_id) REFERENCES public.clients(id);
 Q   ALTER TABLE ONLY public.loan_applications DROP CONSTRAINT fk_client_application;
       public          postgres    false    3184    214    215               �  x�u��N1��ާq���k�����$�-��	���m ͆d�+�߈o����Z�3��f�Z�7����y�x�s^�Q�;�)�?�gܥemYRU9��^`�E��*���:[�i8�U�1FB�M��Z���O��$^�sŷ2��}N�x�h�%�0��4�Q����	��H�����[�`#���ީ��7x3�v@���^;�tU�.�1�������?.�;�X��S�
�k$&g������*,H�8^����j�$o����^<_J\���`<�CM4�z��Y_��bD��M�%�D�H=�3���fm�7JoGa�����x9��r�-�!f����Ơ�����.�$�zɯ�e��r�pɿ�
���T�o"?��r��bJ4��_�[���D��7ʖ�#_7!��:u��P�����::,��<%��         M   x�3�44�4202�5��5��0�¾[.쿰�b��rq�U`�id�W�)��	g�煹�* �Qec���� ��=2         �   x�����0�3��va�; �#�`6�M+B��~�BD��n����l��ᘡ��%䘥�,�d�����b���P�!�
q�tb�~ ��[h]D�ې�B�)Os4�����ܽ4����1�O��,�w�`���O)� dJ��     