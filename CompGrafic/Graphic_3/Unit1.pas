unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs,openGL, math;

type
  TForm1 = class(TForm)
    procedure FormCreate(Sender: TObject);
    procedure FormPaint(Sender: TObject);
    procedure FormResize(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  HRC: HGLRC;
  angle: single;

implementation

{$R *.dfm}
uses DGLUT;
  procedure SetDCPixelFormat ( hdc : HDC );
var
  pfd : TPixelFormatDescriptor;
  nPixelFormat : Integer;
begin
  FillChar (pfd, SizeOf (pfd), 0);
  pfd.dwFlags  := PFD_DRAW_TO_WINDOW or PFD_SUPPORT_OPENGL or PFD_DOUBLEBUFFER;
  nPixelFormat := ChoosePixelFormat (hdc, @pfd);
  SetPixelFormat (hdc, nPixelFormat, @pfd);
end;
Var obj:(TETRAHEGRON,ICOSAHEDRON,DODECAHEDRON,CUBE,SPHERE,CONE,TORUS,TEAPOT)=CUBE;
Var mode:(POINT,LINE,FILL)=FILL;

procedure TForm1.FormCreate(Sender: TObject);
begin
  SetDCPixelFormat(Canvas.Handle); //Применяем формат пиксела заданный ранее
  hrc := wglCreateContext(Canvas.Handle); //Выделяем контекст устройства
  wglMakeCurrent(Canvas.Handle, hrc);
  glEnable(GL_DEPTH_TEST); // включаем проверку разрешения фигур (впереди стоящая закрывает фигуру за ней)
  glDepthFunc(GL_LEQUAL); //тип проверки

end;

procedure TForm1.FormPaint(Sender: TObject);
  var cylinder: GLUquadricObj;
  begin
    FormResize(Sender); //процедура обновления
    if GetAsyncKeyState(VK_UP)<>0 then angle:=angle +0.5;
    if GetAsyncKeyState(VK_DOWN)<>0 then angle:=angle -0.5;
    glRotatef(angle,0,1,0);


    glClearColor (0.5, 0.5, 0.75, 1.0); // цвет фона
    glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT); // очистка буфера цвета

    glpushMatrix; //Запомнили

    cylinder := gluNewQuadric();
    gluQuadricDrawStyle(cylinder, GLU_FILL);
    glColor(0.8,0,0);
    glTranslatef(0,0,-4.5); //Сместил
    gluCylinder(cylinder, 2, 2, 6, 50, 50);
    glpopMatrix();


    glTranslatef(0,0,1.7);
    glutSolidSphere(2,32,32);


    glTranslatef(0,0,-6);
    glutSolidSphere(2,32,32);

    glColor(0.2,0.4,1.0);
    glTranslatef(0,-0.5,9); //Сместили
    glutSolidCube(2.5);

    glColor(0,0,0);
    glTranslatef(0.8,-1.25,0.3);
    glutSolidSphere(0.75,32,32);

    glTranslatef(-1.8,0,0);
    glutSolidSphere(0.75,32,32);

    glTranslatef(-0.3,0,-8.5);
    glutSolidSphere(0.75,32,32);

    glTranslatef(0,0,1.5);
    glutSolidSphere(0.75,32,32);

    glTranslatef(2.5,0,0);
    glutSolidSphere(0.75,32,32);

    glTranslatef(0,0,-1.5);
    glutSolidSphere(0.75,32,32);

    glPopmatrix;  //Восстановили
    glEnable(GL_LIGHTING); //включаем освещение
    glEnable(GL_LIGHT0); //включаем источник света №0
    glEnable(GL_COLOR_MATERIAL);
    SwapBuffers(Canvas.Handle);
  end;

procedure TForm1.FormResize(Sender: TObject);
  begin
    glViewport(0, 0, ClientWidth, ClientHeight); //выделяем область куда будет выводиться наш буфер
    glMatrixMode ( GL_PROJECTION ); //переходим в матрицу проекции
    glLoadIdentity; //Сбрасываем текущую матрицу
    glFrustum ( -1 , 1 , -1 , 1 , 1.25 , 100.0 ); //Область видимости
    glMatrixMode ( GL_MODELVIEW ); // переходим в модельную матрицу
    glLoadIdentity; //Сбрасываем текущую матрицу
    gluLookAt(1,1,10,0,0,0,0,1,0); //позиция наблюдателя
    InvalidateRect ( Handle,nil,False ); //перерисовка формы
  end;
end.
